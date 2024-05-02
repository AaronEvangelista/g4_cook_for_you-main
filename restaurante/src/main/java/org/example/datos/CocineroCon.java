package org.example.datos;

import org.example.usuario.Cocinero;


import java.sql.*;
import java.util.ArrayList;



public class CocineroCon {
    public static  final String USER = "root";
    public static  final String PASSWORD = "root";
    public static  final String SCHEMA = "CFY";
    public static  final String BBDD = "jdbc:mysql://127.0.0.1:3306/";

    public ArrayList<Cocinero> cocineros = new ArrayList<Cocinero>();
    public static final String SELECT_COCINERO = "SELECT id_usuario,nombre, tipo,comensales,telefono, email, password, turno, especialidad from Cocinero";
    public static final String INSERT_COCINERO = " insert into Cocinero(id_usuario, nombre,  tipo,comensales,telefono, email, password, turno, especialidad) values(?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_COCINERO_WHIT_WHERE = "SELECT nombre, tipo,comensales, telefono, email, password, turno, especialidad from Cocinero where id_usuario = ?";
    public static final String UPDATE_COCINERO = "update Cocinero set nombre, tipo,comensales, telefono, email, password, turno, especialidad = ? where id_usuario = ?";
    public static final String DELETE_COCINERO = "delete from Cocinero where id_usuario = ?";


    Connection conn;
    public void init() throws SQLException {
        conn = DriverManager.getConnection( BBDD+SCHEMA, USER, PASSWORD);
        System.out.println("SELECT:");
        for (Cocinero cocinero : select()) {
            System.out.println(cocinero);
        }
        System.out.println("SELECT con WHERE");
        for (Cocinero cocinero : selectWithWhere(3)) {
            System.out.println(cocinero);
        }

        //SELECT
        PreparedStatement ps =
                conn.prepareStatement("select * from Cocinero");
        select();

        //UPDATE
        update(new Cocinero(1,"Aron","Cocinero",694541215,0,"Fran@disney.com","123","mañana","Cocina Libanesa"));

        //añado insert
        insert(new Cocinero(3,"Fran","Cocinero",655484561,0,"Aron#@hh.com","123","Tarde","Cocina Latina"));

        //delete
        delete(1);

        //desconectar
        conn.close();

    }


    //SELECT
    public ArrayList<Cocinero> select() throws SQLException {
        conn = DriverManager.getConnection( BBDD+SCHEMA, USER, PASSWORD);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_COCINERO);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id_usuario = rs.getInt(1);
            String nombre= rs.getString(2);
            String tipo = rs.getString(3);
            int comensales = rs.getInt(4);
            int telefono = rs.getInt(5);
            String email = rs.getString(6);
            String password = rs.getString(7);
            String turno = rs.getString(8);
            String especialidad = rs.getString(9);
            Cocinero cocinero = new Cocinero(id_usuario, nombre, tipo,comensales, telefono, email, password, turno, especialidad);
            cocineros.add(cocinero);


        }
        conn.close();
        return cocineros;
    }


    private ArrayList<Cocinero> selectWithWhere(int id_usuario) throws SQLException {
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_COCINERO_WHIT_WHERE);

        ps.setInt(1, id_usuario);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Cocinero cocinero = new Cocinero
                    (rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("tipo"),
                            rs.getInt("comensales"),
                            rs.getInt("telefono"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("turno"),
                            rs.getString("especialidad"));
            cocineros.add(cocinero);
        }
        return cocineros;
    }

    public void insert(Cocinero cocinero) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_COCINERO);

        ps.setInt(1, cocinero.getId_usuario());
        ps.setString(2, cocinero.getNombre());
        ps.setString(3, cocinero.getTipo());
        ps.setInt(4, cocinero.getComensales());
        ps.setInt(5, cocinero.getTelefono());
        ps.setString(6, cocinero.getEmail());
        ps.setString(7, cocinero.getPassword());
        ps.setString(8, cocinero.getTurno());
        ps.setString(9, cocinero.getEspecialidad());
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR INSERT");
        }

    }

    public void delete(int id_usuario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(DELETE_COCINERO);
        ps.setInt(1, id_usuario);
        System.out.println(ps.executeUpdate());
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR DELETE");
        }
    }

    public void update(Cocinero cocinero) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(UPDATE_COCINERO);

        ps.setInt(1, cocinero.getId_usuario());
        ps.setInt(1, cocinero.getId_usuario());
        ps.setString(2, cocinero.getNombre());
        ps.setString(3, cocinero.getTipo());
        ps.setInt(4, cocinero.getComensales());
        ps.setInt(5, cocinero.getTelefono());
        ps.setString(6, cocinero.getEmail());
        ps.setString(7, cocinero.getPassword());
        ps.setString(8, cocinero.getTurno());
        ps.setString(9, cocinero.getEspecialidad());

        System.out.println(ps.toString());
        int result = ps.executeUpdate();
        if (result == 1) {
            System.out.println("ERROR: No hay nada que actualizar");
        }

    }







}


