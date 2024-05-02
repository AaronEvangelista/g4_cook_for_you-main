package org.example.datos;

import org.example.restaurante.*;
import org.example.usuario.*;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Restaurante {
    private static List<Cliente> clientes;
    private static List<Cocinero> cocineros;
    private static List<Administrador> administradors;
    private static List<Camarero> camareros;
    private static List<Menu> menus;
    private static List<Producto> productos;
    private static List<Mesa> mesas;
    private static List<Plato> platos;
    private static List<Comanda> comandas;

    public static String PATH = "C:\\Users\\Usuario\\IdeaProjects\\g4_cook_for_you2\\restaurante\\src\\main\\java\\org\\example\\ficheros\\";
    public static String FILE_CLIENTE = PATH + "clientes.csv";
    public static String FILE_CAMARERO = PATH + "Camareros.csv";
    public static String FILE_COCINERO = PATH + "Cocineros.csv";
    public static String FILE_ADMINISTRADOR = PATH + "Administrador.csv";
    public static String FILE_MENU = PATH + "menu.csv";
    public static String FILE_MESA = PATH + "mesas.csv";
    public static String FILE_PLATO = PATH + "plato.csv";
    public static String FILE_COMANDA = PATH + "Comanda.csv";
    public static String FILE_ALERGENOS = PATH + "Alergenos.csv";
    public static String FILE_PLATOING = PATH + "plato_ingrediente.csv";
    public static String FILE_MENUPLATO = PATH + "menu_plato.csv";
    public static String FILE_USUARIO = PATH + "usuarios.csv";
    public static String FILE_INGREDIENTE = PATH + "ingredientes.csv";
    public static String FILE_ADMIN = PATH + "admin.csv";
    public static void main(String[] args) throws SQLException {
        //DatabaseCon databaseC = new DatabaseCon();

        //CocineroCon cocineroC = new CocineroCon();
        ClienteCon clienteC = new ClienteCon();
        //AdministradorCon administradorC = new AdministradorCon();
        //CamareroCon camareroC = new CamareroCon();
        UsuarioCon usuarioC = new UsuarioCon();


        //cocineroC.select();
        clienteC.init();
        //administradorC.init();
        //camareroC.init();
        usuarioC.init();


        int opcion = menuRestaurante();

        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    atenderCliente();
                    break;
                case 2:
                    buscarUsuario();
                    break;
                case 3:
                    buscarCliente();
                    break;
                case 4:
                    registrarCliente();
                    break;
                case 5:
                    buscarMesa();
                    break;
                case 6:
                    buscarMenu();
                    break;
                case 7:
                    buscarPlatos();
                    break;
                case 8:
                    buscarComanda();
                    break;
                case 9:
                    turnoTrabajadores();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            opcion = menuRestaurante();
        }

    }



    private static int menuRestaurante() {
        Scanner leer = new Scanner(System.in);
        System.out.println("");
        System.out.println("--------|APP DE RESTAURANTES|--------");
        System.out.println("1. Atender Cliente");
        System.out.println("2. Buscar Usuario");
        System.out.println("3. Buscar Cliente");
        System.out.println("4. Registrar Cliente");
        System.out.println("5. Buscar Mesa");
        System.out.println("6. Buscar Menu");
        System.out.println("7. Buscar Plato");
        System.out.println("8. Buscar Comanda");
        System.out.println("9. Turno Trabajadores");
        System.out.println("0. Salir");
        System.out.println("---------------------------------------");
        int opcion = leer.nextInt();
        return opcion;
    }
    private static void atenderCliente() {

    }


    private static void buscarUsuario() throws SQLException {
        Scanner leer = new Scanner(System.in);
        String nombre;
        VolcadoDeDatos volcado = new VolcadoDeDatos();
        System.out.println("");
        System.out.println("--------|APP DE RESTAURANTES|--------");
        System.out.println("Buscar Usuario: ");
        nombre = leer.next();
        List<Usuario> listaUsuario = volcado.buscarUsuario(FILE_USUARIO, nombre);
        if (!listaUsuario.isEmpty()) {
            volcado.buscarClientes(FILE_USUARIO, listaUsuario.toString());
        }
        for (Usuario usuario : listaUsuario) {
            System.out.println(usuario.toString());
        }
        UsuarioCon usuarioC = new UsuarioCon();
        usuarioC.init();
        for (Usuario usuario : listaUsuario) {
            usuarioC.insert(usuario);
        }

    }



    private static void buscarCliente() throws SQLException {
        Scanner leer = new Scanner(System.in);
        String nombre;
        VolcadoDeDatos volcado = new VolcadoDeDatos();
        System.out.println("");
        System.out.println("--------|APP DE RESTAURANTES|--------");
        System.out.println("Buscar Cliente: ");
        nombre = leer.next();
        List<Cliente> listaCliente = volcado.buscarClientes(FILE_CLIENTE, nombre);
        if (!listaCliente.isEmpty()) {
            volcado.buscarClientes(FILE_CLIENTE, listaCliente.toString());
        }
        for (Cliente cliente : listaCliente) {
            System.out.println(cliente.toString());
        }
        ClienteCon clienteC = new ClienteCon();
        clienteC.init();
        for (Cliente cliente : listaCliente) {
            clienteC.insert(cliente);
        }
    }

    private static void registrarCliente() {
    }

    private static void buscarMesa() {
        Scanner leer = new Scanner(System.in);
        String nombre;
        VolcadoDeDatos volcado = new VolcadoDeDatos();
        System.out.println("");
        System.out.println("--------|APP DE RESTAURANTES|--------");
        System.out.println("Buscar Mesa: ");
        nombre = leer.next();
        List<Mesa> listaMesa = volcado.buscarMesa(FILE_MESA, nombre);
        if (!listaMesa.isEmpty()) {
            volcado.buscarClientes( FILE_MESA, listaMesa.toString());
        }
        for (Mesa mesa : listaMesa) {
            System.out.println(mesa);
        }
    }

    private static void buscarMenu() {
        Scanner leer = new Scanner(System.in);
        String nombre;
        VolcadoDeDatos volcado = new VolcadoDeDatos();
        System.out.println("");
        System.out.println("--------|APP DE RESTAURANTES|--------");
        System.out.println("Buscar Menu: ");
        nombre = leer.next();
        List<Menu> listaMenu = volcado.buscarMenu(FILE_MENU, nombre);
        if (!listaMenu.isEmpty()) {
            volcado.buscarClientes( FILE_MENU, listaMenu.toString());
        }
        for (Menu menu : listaMenu) {
            System.out.println(menu);
        }

    }

    private static void buscarPlatos() {
        Scanner leer = new Scanner(System.in);
        String nombre;
        VolcadoDeDatos volcado = new VolcadoDeDatos();
        System.out.println("");
        System.out.println("--------|APP DE RESTAURANTES|--------");
        System.out.println("Buscar Plato: ");
        nombre = leer.next();
        List<Plato> listaPlato = volcado.buscarPlato(FILE_PLATO, nombre);
        if (!listaPlato.isEmpty()) {
            volcado.buscarClientes( FILE_PLATO, listaPlato.toString());
        }
        for (Plato plato : listaPlato) {
            System.out.println(plato);
        }

    }

    private static void buscarComanda() {
        Scanner leer = new Scanner(System.in);
        String nombre;
        VolcadoDeDatos volcado = new VolcadoDeDatos();
        System.out.println("");
        System.out.println("--------|APP DE RESTAURANTES|--------");
        System.out.println("Buscar Comanda: ");
        nombre = leer.next();
        List<Comanda> listaComanda = volcado.buscarComanda(FILE_COMANDA, nombre);
        if (!listaComanda.isEmpty()) {
            volcado.buscarClientes( FILE_COMANDA, listaComanda.toString());
        }
        for (Comanda comanda : listaComanda) {
            System.out.println(comanda);
        }

    }

    private static void turnoTrabajadores() {

    }

}
