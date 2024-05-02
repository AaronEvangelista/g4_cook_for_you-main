package org.example.datos;

import org.example.usuario.Camarero;

import java.sql.*;
import java.util.ArrayList;



public class CamareroCon {
    public static  final String USER = "root";
    public static  final String PASSWORD = "root";
    public static  final String SCHEMA = "CFY";
    public static  final String BBDD = "jdbc:mysql://localhost:3306/";


    public ArrayList<Camarero> camareros;
    public static final String SELECT_CAMARERO = "SELECT * from Camarero";
    public static final String INSERT_CAMARERO = " insert into Camarero(id_usuario, nombre, tipo,comensales, telefono, email, password, turno) values(?,?,?,?,?,?,?,?)";
    public static final String SELECT_CAMARERO_WHIT_WHERE = "SELECT  nombre, tipo,comensales, telefono, email, password, turno  from Camarero where id_usuario = ?";
    public static final String UPDATE_CAMARERO = "update Camarero set  nombre, tipo,comensales, telefono, email, password, turno = ? where id_usuario = ?";
    public static final String DELETE_CAMARERO = "delete from Camarero where id_usuario = ?";

    Connection conn;
    public void init() throws SQLException {
        conn = DriverManager.getConnection( BBDD+SCHEMA, USER, PASSWORD);
        System.out.println("SELECT:");
        for (Camarero camarero : camareros) {
            System.out.println(camarero);
        }
        System.out.println("SELECT con WHERE");
        for (Camarero camarero : selectWithWhere(3)) {
            System.out.println(camarero);
        }

        //SELECT
        PreparedStatement ps =
                conn.prepareStatement("select * from Camarero");
        select();

        //UPDATE
        update(new Camarero(1,"Fran","Camarero",456484512,0,"Fran@desde.com","113","Mañana"));

        //añado insert
        insert(new Camarero(3,"Aroon","Camarero",698512331,0,"Aroon@desde.com","113","Mañana"));

        //delete
        delete(1);

        //desconectar
        conn.close();

    }


    //SELECT
    private ArrayList<Camarero> select() throws SQLException {
        ArrayList<Camarero> clientes = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_CAMARERO);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id_usuario = rs.getInt(1);
            String nombre = rs.getString(2);
            String tipo = rs.getString(3);
            int comensales = Integer.parseInt(rs.getString(4));
            int telefono = Integer.parseInt(rs.getString(5));
            String email = rs.getString(6);
            String password = rs.getString(7);
            String turno = rs.getString(8);
            Camarero camarero = new Camarero(id_usuario, nombre, tipo,comensales,telefono, email, password,turno );
            clientes.add(camarero);


        }
        return clientes;
    }



    private ArrayList<Camarero> selectWithWhere(int id_usuario) throws SQLException {
        ArrayList<Camarero> camareros = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_CAMARERO_WHIT_WHERE);

        ps.setInt(1, id_usuario);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Camarero camarero = new Camarero
                    (rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("tipo"),
                            rs.getInt("comensales"),
                            rs.getInt("telefono"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("turno"));
            camareros.add(camarero);
        }
        return camareros;
    }

    public void insert(Camarero camarero) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_CAMARERO);

        ps.setInt(1, camarero.getId_usuario());
        ps.setString(2, camarero.getNombre());
        ps.setString(3, camarero.getTipo());
        ps.setInt(4, camarero.getComensales());
        ps.setInt(5, camarero.getTelefono());
        ps.setString(6, camarero.getEmail());
        ps.setString(7, camarero.getPassword());
        ps.setString(8, camarero.getTurno());
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR INSERT");
        }

    }

    public void delete(int id_usuario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(DELETE_CAMARERO);
        ps.setInt(1, id_usuario);
        System.out.println(ps.executeUpdate());
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR DELETE");
        }
    }

    public void update(Camarero camarero) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(UPDATE_CAMARERO);

        ps.setInt(1, camarero.getId_usuario());
        ps.setString(2, camarero.getNombre());
        ps.setString(3, camarero.getTipo());
        ps.setInt(4, camarero.getComensales());
        ps.setInt(5, camarero.getTelefono());
        ps.setString(6, camarero.getEmail());
        ps.setString(7, camarero.getPassword());
        ps.setString(8, camarero.getTurno());
        System.out.println(ps.toString());
        int result = ps.executeUpdate();
        if (result == 1) {
            System.out.println("ERROR: No hay nada que actualizar");
        }

    }







}


