package org.example.datos;

import org.example.restaurante.*;
import org.example.usuario.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseCon {
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String DATABASE = "CFY";
    public static final String BBDD = "jdbc:mysql://localhost:3307/";
    public static final String INSERT_USERS = "insert into users values(?,?,?,?,?,?)";
    public static final String PATH = "C:\\Users\\Usuario\\IdeaProjects\\g4_cook_for_you2\\restaurante\\src\\main\\java\\org\\example\\ficheros\\";
    public ArrayList<Usuario> usuarios;


    private Connection conn;

    public void init() throws SQLException {
        conn = DriverManager.getConnection(BBDD + DATABASE, USER, PASSWORD); //root conectado
        //cargar datos
  /*      cargarCSV();

        conn.close();
        insertarUsers();
    }

    private void cargarCSV() {
        //RUTAS
        String rutaCSVuser = PATH+"usuarios.csv";

        //Mostramos los datos
        for (Usuario usuario : select()){
            System.out.println(usuario);
            insertarUsers();
        }

    }

    private void insertarUsers() {

    }*/


    }
}