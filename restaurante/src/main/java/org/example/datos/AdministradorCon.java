package org.example.datos;

import org.example.usuario.Administrador;


import java.sql.*;
import java.util.ArrayList;

public class AdministradorCon {
    public static  final String USER = "root";
    public static  final String PASSWORD = "root";
    public static  final String SCHEMA = "CFY";
    public static  final String BBDD = "jdbc:mysql://localhost:3306/";


    public ArrayList<Administrador> administradores;
    public static final String SELECT_ADMINISTRADOR = "SELECT * from Administrador";
    public static final String INSERT_ADMINISTRADOR = " insert into Administrador(id_usuario, nombre,tipo, comensales, telefono, email, password, turno, cobro_comanda) values(?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_ADMINISTRADOR_WHIT_WHERE = "SELECT nombre,tipo, comensales, telefono, email, password, turno, cobro_comanda from Administrador where id_usuario = ?";
    public static final String UPDATE_ADMINISTRADOR = "update Administrador set nombre,tipo, comensales, telefono, email, password, turno, cobro_comanda = ? where id_usuario = ?";
    public static final String DELETE_ADMINISTRADOR = "delete from Administrador where id_usuario = ?";

    Connection conn;
    public void init() throws SQLException {
        conn = DriverManager.getConnection( BBDD+SCHEMA, USER, PASSWORD);
       /* System.out.println("SELECT:");
        for (Administrador administrador : select()) {
            System.out.println(administrador);
        }
        System.out.println("SELECT con WHERE");
        for (Administrador administrador : selectWithWhere(3)) {
            System.out.println(administrador);
        }

        //SELECT
        PreparedStatement ps =
                conn.prepareStatement("select * from Administrador");
        select();

        //UPDATE
        update(new Administrador(1,2,"Fran","Administrador","Mañana"));

        //añado insert
        insert(new Administrador(3,2,"Aron","Administrador","Tarde"));

        //delete
        delete(1);

        //desconectar
        conn.close();*/

    }


    //SELECT
    private ArrayList<Administrador> select() throws SQLException {
        ArrayList<Administrador> administradores = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_ADMINISTRADOR);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id_usuario = rs.getInt(1);
            String nombre = rs.getString(2);
            String tipo = rs.getString(3);
            int comensales = rs.getInt(4);
            int telefono = rs.getInt(5);
            String email = rs.getString(6);
            String password = rs.getString(7);
            String turno = rs.getString(6);
            double cobro_comanda = rs.getInt(8);
            Administrador administrador= new Administrador(id_usuario, nombre, tipo,comensales,telefono,email, password, turno, cobro_comanda);
            administradores.add(administrador);


        }
        return administradores;
    }


    private ArrayList<Administrador> selectWithWhere(int id) throws SQLException {
        ArrayList<Administrador> administradores = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_ADMINISTRADOR_WHIT_WHERE);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Administrador administrador = new Administrador
                    (rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("tipo"),
                            rs.getInt("comensales"),
                            rs.getInt("telefono"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("turno"),
                            rs.getDouble("cobro_comanda"));
            administradores.add(administrador);
        }
        return administradores;
    }

    public void insert(Administrador administrador) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_ADMINISTRADOR);

        ps.setInt(1, administrador.getId_usuario());
        ps.setString(2, administrador.getNombre());
        ps.setString(3, administrador.getTipo());
        ps.setInt(4, administrador.getComensales());
        ps.setInt(5, administrador.getTelefono());
        ps.setString(6, administrador.getEmail());
        ps.setString(7, administrador.getPassword());
        ps.setString(8, administrador.getTurno());
        ps.setDouble(9, administrador.getCobro_comanda());

        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR INSERT");
        }

    }

    public void delete(int idUsuario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(DELETE_ADMINISTRADOR);
        ps.setInt(1, idUsuario);
        System.out.println(ps.executeUpdate());
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR DELETE");
        }
    }

    public void update(Administrador administrador) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(UPDATE_ADMINISTRADOR);

        ps.setInt(1, administrador.getId_usuario());
        ps.setString(2, administrador.getNombre());
        ps.setString(3, administrador.getTipo());
        ps.setInt(4, administrador.getComensales());
        ps.setInt(5, administrador.getTelefono());
        ps.setString(6, administrador.getEmail());
        ps.setString(7, administrador.getPassword());
        ps.setString(8, administrador.getTurno());
        ps.setDouble(9, administrador.getCobro_comanda());

        System.out.println(ps.toString());
        int result = ps.executeUpdate();
        if (result == 1) {
            System.out.println("ERROR: No hay nada que actualizar");
        }

    }
}
