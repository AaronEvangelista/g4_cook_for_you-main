package org.example.datos;


import org.example.usuario.Cocinero;
import org.example.usuario.Usuario;

import java.sql.*;
import java.util.ArrayList;



public class UsuarioCon {
    public static  final String USER = "root";
    public static  final String PASSWORD = "root";
    public static  final String SCHEMA = "CFY";
    public static  final String BBDD = "jdbc:mysql://127.0.0.1:3306/";


    public ArrayList<Usuario> usuarios;
    public static final String SELECT_USUARIO = "SELECT *from Usuario";
    public static final String INSERT_USUARIO = " insert into Usuario(id_usuario, nombre, tipo, comensales, telefono, email, password) values(?,?,?,?,?,?,?)";
    public static final String SELECT_USUARIO_WHIT_WHERE = "SELECT nombre, tipo, telefono  from Usuario where id_usuario = ?";
    public static final String UPDATE_USUARIO = "update Usuario set nombre, tipo, telefono = ? where id_usuario = ?";
    public static final String DELETE_USUARIO = "delete from Usuario where id_usuario = ?";

    Connection conn;
    public void init() throws SQLException {
        conn = DriverManager.getConnection( BBDD+SCHEMA, USER, PASSWORD);
       /* System.out.println("SELECT:");
        for (Usuario usuario : select()) {
            System.out.println(usuario);
        }
        System.out.println("SELECT con WHERE");
        for (Usuario usuario : selectWithWhere(3)) {
            System.out.println(usuario);
        }

        //SELECT
        PreparedStatement ps =
                conn.prepareStatement("select * from Usuarios");
        select();

        //UPDATE
        update(new Usuario(1,"Fran","Cliente"));

        //añado insert
        insert(new Usuario(3,"Aroon","Cliente"));

        //delete
        delete(1);

        //desconectar
        conn.close();*/

    }


    //SELECT
    private ArrayList<Usuario> select() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_USUARIO);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id_usuario = rs.getInt(1);
            String nombre = rs.getString(2);
            String tipo = rs.getString(3);
            int comensales = rs.getInt(4);
            int telefono = rs.getInt(5);
            String email = rs.getString(6);
            String password = rs.getString(7);
            Usuario usuario = new Usuario(id_usuario, nombre, tipo,comensales, telefono, email, password);
            usuarios.add(usuario);


        }
        return usuarios;
    }


    private ArrayList<Usuario> selectWithWhere(int id_usuario) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_USUARIO_WHIT_WHERE);

        ps.setInt(1, id_usuario);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario
                    (rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("tipo"),
                            rs.getInt("comensales"),
                            rs.getInt("telefono"),
                            rs.getString("email"),
                            rs.getString("password"));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public void insert(Usuario usuario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_USUARIO);

        ps.setInt(1, usuario.getId_usuario());
        ps.setString(2, usuario.getNombre());
        ps.setString(3, usuario.getTipo());
        ps.setInt(4, usuario.getComensales());
        ps.setInt(5, usuario.getTelefono());
        ps.setString(6, usuario.getEmail());
        ps.setString(7, usuario.getPassword());
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR INSERT");
        }

    }

    public void delete(int id_usuario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(DELETE_USUARIO);
        ps.setInt(1, id_usuario);
        System.out.println(ps.executeUpdate());
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR DELETE");
        }
    }

    public void update(Usuario usuario) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(UPDATE_USUARIO);

        ps.setInt(1, usuario.getId_usuario());
        ps.setString(2, usuario.getNombre());
        ps.setString(3, usuario.getTipo());
        ps.setInt(4, usuario.getComensales());
        ps.setInt(5, usuario.getTelefono());
        ps.setString(6, usuario.getEmail());
        ps.setString(7, usuario.getPassword());

        System.out.println(ps.toString());
        int result = ps.executeUpdate();
        if (result == 1) {
            System.out.println("ERROR: No hay nada que actualizar");
        }

    }







}


