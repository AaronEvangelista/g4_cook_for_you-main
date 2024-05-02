
#VerificarUsuario
DELIMITER //
 CREATE FUNCTION validacionCorreoYContraseña(correo VARCHAR(100), contraseña VARCHAR(20))
 RETURNS BOOLEAN DETERMINISTIC BEGIN     
 DECLARE valido BOOLEAN;          
 IF correo REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$' THEN        
 IF contraseña REGEXP '^[0-9a-zA-Z]+$' THEN            
 SET valido = TRUE;         
 ELSE            
 SET valido = FALSE;         
 END IF;    
 ELSE         
 SET valido = FALSE;    
 END IF;      
RETURN valido; 
END ;
// DELIMITER ;


#AsignarMesa
DELIMITER //
CREATE PROCEDURE AsignarMesa(IN numComensales INT)
BEGIN
    DECLARE mesaDisponible INT;
    SELECT id_mesa INTO mesaDisponible
    FROM Mesa
    WHERE capacidad >= numComensales AND estado = 0 
    LIMIT 1;
    
    IF mesaDisponible IS NOT NULL THEN
        UPDATE Mesa
        SET estado = 1 
        WHERE id_mesa = mesaDisponible;
        
        INSERT INTO Cliente (num_comensales)
        VALUES (mesaDisponible, numComensales);
	
        SELECT CONCAT('Cliente asignado a la mesa ', mesaDisponible) AS mensaje;
    ELSE
        SELECT 'No hay mesas disponibles para el número de comensales especificado' AS mensaje;
    END IF;
END//
DELIMITER ;

#ListarMesas
SELECT m.id_mesa, 
       CASE WHEN m.estado = 1 THEN 'libre' ELSE 'ocupada' END AS disponibilidad,
       COUNT(c.id_usuario) AS num_comensales
FROM Mesa m
LEFT JOIN Cliente c ON m.id_mesa = id_mesa
GROUP BY m.id_mesa, m.estado
ORDER BY m.id_mesa;


#Lista las comandas (especificar turno y ordenar por turno).

SELECT * FROM comanda where turno_comanda  = 'añadir turno de comanda' ORDER BY turno_comanda;

#Listar todos los camareros/as y cocineros/as y las mesas o platos que tienen asignados (Function)
#camareros
DELIMITER //
CREATE FUNCTION listarCamarerosMesas() RETURNS TEXT DETERMINISTIC
DETERMINISTIC BEGIN
    DECLARE resultado TEXT;
    SELECT GROUP_CONCAT(CONCAT(u.nombre, ' ', ' - Mesa: ', m.num_mesa) SEPARATOR '\n') INTO resultado
    FROM Usuario u
    INNER JOIN Camarero c ON u.id = c.id_usuario
    INNER JOIN Mesa m ON c.id_usuario = m.id_camarero;
    RETURN resultado;
END //
DELIMITER ;


#cocinero
DELIMITER //
CREATE FUNCTION listarCocinerosPlatos() RETURNS TEXT DETERMINISTIC
DETERMINISTIC BEGIN  
    DECLARE resultado TEXT;
    SELECT GROUP_CONCAT(CONCAT(u.nombre, ' ', ' - Plato: ', p.nombre_plato) SEPARATOR '\n') 
    INTO resultado
    FROM Usuario u
    INNER JOIN Cocinero c ON Usuario.id = c.id_usuario
    INNER JOIN Plato p ON c.id_usuario = p.id_chef;
    RETURN resultado;
END //
DELIMITER ;



#Listar los menús y los platos que tiene cada menú ordenado por ID

SELECT m.id_menu, m.nombre, p.id_plato, p.nombre_plato
FROM Menu m
LEFT JOIN Menu_Plato mp ON m.id_menu = mp.id_menu
LEFT JOIN Plato p ON mp.id_plato = p.id_plato
ORDER BY m.id_menu;

#Añadir o quitar platos a un menú especificando el identificador del plato y del menú añadiendo si no existe la combinación y eliminando si ya existe (Function)

DELIMITER //
CREATE PROCEDURE gestionarPlatosMenu(
    IN p_id_menu INT,
    IN p_id_plato INT
)
BEGIN
    DECLARE v_count INT;
    SELECT COUNT(*) INTO v_count
    FROM Menu_Plato
    WHERE id_menu = p_id_menu AND id_plato = p_id_plato;

    IF v_count > 0 THEN
        SELECT 'La combinación ya existe en Menu_Plato.';
    ELSE
        INSERT INTO Menu_Plato (id_menu, id_plato)
        VALUES (p_id_menu, p_id_plato);
        SELECT 'Combinación agregada a Menu_Plato.';
    END IF;
END//
DELIMITER ;

#Listar platos ordenados por nombre.

SELECT * FROM PLATO ORDER BY nombre_plato;

#Mostrar los ingredientes que tiene cada plato ordenados alfabéticamente:

SELECT p.nombre_plato, pr.nombre_producto
FROM Plato p
INNER JOIN Producto_Plato pp ON p.id_plato = pp.id_plato
INNER JOIN Producto pr ON pp.id_producto = pr.id_producto
ORDER BY p.nombre_plato, pr.nombre_producto;

#Mostrar alérgenos de los platos sin repeticiones:

SELECT DISTINCT alergenos FROM Plato;

#Listar todos los ingredientes y el stock que tiene:

SELECT p.nombre_producto, p.stock FROM Producto p;

#Crear o modificar un plato especificando sus ingredientes (Stored Procedure).

DELIMITER //
CREATE PROCEDURE CrearYModificarPlato(IN p_id_plato INT, IN p_nombre_plato VARCHAR(255), IN p_descripcion TEXT, IN p_id_cocinero INT, IN p_id_producto INT)
BEGIN
    DECLARE existe BOOLEAN;
    SELECT EXISTS(SELECT * FROM Plato WHERE id_plato = p_id_plato) INTO existe;

    IF existe THEN
        UPDATE Plato SET nombre_plato = p_nombre_plato, descripcion = p_descripcion, id_cocinero = p_id_cocinero WHERE id_plato = p_id_plato;
        IF NOT EXISTS(SELECT * FROM Plato_Producto WHERE id_plato = p_id_plato AND id_producto = p_id_producto) THEN
            INSERT INTO Plato_Producto(id_plato, id_producto) VALUES (p_id_plato, p_id_producto);
        END IF;
    ELSE
        INSERT INTO Plato(id_plato, nombre_plato, descripcion, id_chef) VALUES (p_id_plato, p_nombre_plato, p_descripcion, p_id_chef);
        INSERT INTO Plato_Producto(id_plato, id_producto) VALUES (p_id_plato, p_id_producto);
    END IF;
END //
DELIMITER ;

#Al añadir ítems a una nueva comanda, se debe verificar que haya stock y que se reste en función de los ingredientes y que no esté cerrada (Stored Procedure).

DELIMITER //

CREATE PROCEDURE VerificarStock(IN p_id_comanda INT, IN p_id_menu INT, OUT mensaje VARCHAR(255))
BEGIN
    DECLARE estaCerrada BOOLEAN;
    DECLARE stockSuficiente BOOLEAN DEFAULT TRUE;
    DECLARE id_producto INT;
    DECLARE stockActual INT;
    DECLARE cur CURSOR FOR SELECT id_producto FROM Menu_Plato JOIN Plato_Producto ON Menu_Plato.id_plato = Plato_Producto.id_plato WHERE id_menu = p_id_menu;
 
    SELECT pagado INTO estaCerrada FROM Comanda WHERE id_comanda = p_id_comanda;
    IF estaCerrada THEN
        SET mensaje = 'La comanda ya está cerrada';
        LEAVE VerificarStockProcedure; 
    END IF;

    OPEN cur;
    FETCH NEXT FROM cur INTO id_producto;
    WHILE stockSuficiente AND id_producto IS NOT NULL DO
        SELECT stock INTO stockActual FROM Producto WHERE id_producto = id_producto;
        IF stockActual <= 0 THEN
            SET stockSuficiente = FALSE;
        END IF;
        FETCH NEXT FROM cur INTO id_producto;
    END WHILE;
    CLOSE cur;

    IF NOT stockSuficiente THEN
        SET mensaje = 'No hay stock suficiente para uno o más productos del menú';
        LEAVE VerificarStockProcedure;
    ELSE
        SET mensaje = 'Stock verificado, puede proceder con la comanda';
    END IF;
END //

DELIMITER ;

CALL VerificarStock(1, 123, @mensaje);
SELECT @mensaje;



#Calcular el precio total de una comanda (Function):

DELIMITER //
CREATE FUNCTION CalcularPrecioTotalComanda(p_id_comanda INT) RETURNS DECIMAL(10,2)
BEGIN
    DECLARE precioTotal DECIMAL(10,2);

    SELECT SUM(MENU.precio)
    INTO precioTotal
    FROM MENU_COMANDA
    JOIN MENU ON MENU_COMANDA.id_menu = MENU.id_menu
    WHERE MENU_COMANDA.id_comanda = p_id_comanda;

    RETURN precioTotal;
END //
DELIMITER ;

SELECT CalcularPrecioTotalComanda(1);

#Mostrar la facturación que ha hecho una mesa por día con validaciones (Stored Procedure):

DELIMITER //
CREATE PROCEDURE FacturacionMesaPorDia(IN p_id_mesa INT, IN p_fecha DATE, OUT facturacion DECIMAL(10,2))
BEGIN
    IF NOT EXISTS(SELECT * FROM MESA WHERE id_mesa = p_id_mesa) THEN
        SET facturacion = -1;
        LEAVE FacturacionMesaPorDia;
    END IF;
    
    SELECT SUM(CalcularPrecioTotalComanda(COMANDA.id_comanda))
    INTO facturacion
    FROM COMANDA
    WHERE COMANDA.id_mesa = p_id_mesa AND DATE(COMANDA.fecha) = p_fecha;
END //
DELIMITER ;

CALL FacturacionMesaPorDia(1, '2022-04-28', @facturacion);
SELECT @facturacion;



#Cambiar el estado de una mesa de disponible a no disponible o viceversa:

UPDATE MESA SET disponibilidad = NOT disponibilidad WHERE num_mesa = 'introduce num_mesa';

#Asignar, reasignar o desasignar mesa a un trabajador/a:

DELIMITER //
CREATE PROCEDURE AsignarReasignarDesasignarMesa(IN p_id_trabajador INT, IN p_num_mesa INT, IN p_accion VARCHAR(255))
BEGIN
    -- Verificar la acción
    CASE p_accion
        WHEN 'asignar' THEN
            -- Asignar la mesa al trabajador
            UPDATE MESA SET id_camarero = p_id_trabajador WHERE num_mesa = p_num_mesa;
        WHEN 'reasignar' THEN
            -- Reasignar la mesa a otro trabajador
            UPDATE MESA SET id_camarero = p_id_trabajador WHERE num_mesa = p_num_mesa;
        WHEN 'desasignar' THEN
            -- Desasignar la mesa del trabajador
            UPDATE MESA SET id_camarero = NULL WHERE num_mesa = p_num_mesa;
    END CASE;
END //
DELIMITER ;
