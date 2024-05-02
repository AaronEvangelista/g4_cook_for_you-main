package org.example.datos;

import org.example.usuario.Cliente;

import java.sql.*;
import java.util.ArrayList;



public class ClienteCon {
    public static  final String USER = "root";
    public static  final String PASSWORD = "root";
    public static  final String SCHEMA = "CFY";
    public static  final String BBDD = "jdbc:mysql://127.0.0.1:3306/";


    public ArrayList<Cliente> clientes;
    public static final String SELECT_CLIENTE = "SELECT * from Cliente";
    public static final String INSERT_CLIENTE = " insert into Cliente(id_usuario,nombre, tipo,num_comensales,telefono, email, password, alergia) values(?,?,?,?,?,?,?,?)";
    public static final String SELECT_CLIENTE_WHIT_WHERE = "SELECT  nombre, tipo,  comensales, telefono, alergias,  email, password  from Cliente where id_usuario = ?";
    public static final String UPDATE_CLIENTE = "update Cliente set  tipo, num_comensales,telefono, email, password, alergia = ? where id_usuario = ?";
    public static final String DELETE_CLIENTE = "delete from Cliente where id_usuario = ?";

    Connection conn;
    public void init() throws SQLException {
    conn = DriverManager.getConnection( BBDD + SCHEMA, USER, PASSWORD);

        //desconectar
       // conn.close();

    }


    //SELECT
    private ArrayList<Cliente> select() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps =
                conn.prepareStatement(SELECT_CLIENTE);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id_usuario = rs.getInt(1);
            String nombre = rs.getString(2);
            String tipo = rs.getString(3);
            int comensales = rs.getInt(4);
            int telefono = rs.getInt(5);
            String email = rs.getString(5);
            String password = rs.getString(6);
            String alergia = rs.getString(7);
            Cliente cliente = new Cliente(id_usuario,  nombre, tipo, comensales, telefono, email, password, alergia);
            clientes.add(cliente);


        }
        return clientes;
    }


    private ArrayList<Cliente> selectWithWhere(int id) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps =
            conn.prepareStatement(SELECT_CLIENTE_WHIT_WHERE);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Cliente cliente = new Cliente
                (rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("Tipo"),
                        rs.getInt("comensales"),
                        rs.getInt("telefono"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("alergia"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public  void insert(Cliente cliente) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_CLIENTE);


        ps.setInt(1, cliente.getId_usuario());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getTipo());
        ps.setInt(4, cliente.getComensales());
        ps.setInt(5, cliente.getTelefono());
        ps.setString(6, cliente.getEmail());
        ps.setString(7, cliente.getPassword());
        ps.setString(8, cliente.getAlergias());

        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR INSERT");
        }

    }

    public void delete(int id_usuario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(DELETE_CLIENTE);
        ps.setInt(1, id_usuario);
        System.out.println(ps.executeUpdate());
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR DELETE");
        }
    }

    public void update(Cliente cliente) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(UPDATE_CLIENTE);

        ps.setInt(1, cliente.getId_usuario());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getTipo());
        ps.setInt(4, cliente.getComensales());
        ps.setInt(5, cliente.getTelefono());
        ps.setString(6, cliente.getEmail());
        ps.setString(7, cliente.getPassword());
        ps.setString(8, cliente.getAlergias());

        System.out.println(ps.toString());
        int result = ps.executeUpdate();
        if (result == 1) {
            System.out.println("ERROR: No hay nada que actualizar");
        }

}







}

