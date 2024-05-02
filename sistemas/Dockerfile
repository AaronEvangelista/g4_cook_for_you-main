
# Usa la imagen base desde Docker Hub
FROM franmartinez85/cook_sql

# Exponemos el puerto 3306 para que se pueda acceder a MySQL desde fuera del contenedor
EXPOSE 3306

# Establecemos las variables de entorno para MySQL
ENV MYSQL_ROOT_PASSWORD=root

# Copia el archivo SQL al directorio de inicialización de MySQL
COPY ../BBDD/ScriptInicial.sql /docker-entrypoint-initdb.d/
