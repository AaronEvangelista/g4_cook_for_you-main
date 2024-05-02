package org.example.usuario;

public class Usuario {

    private int id_usuario;
    private String nombre;
    private String tipo;
    private int telefono;
    private int comensales;
    private String email;
    private String password;


    public Usuario(int id_usuario, String nombre, String tipo, int comensales, int telefono, String email, String password) {
        setId_usuario(id_usuario);
        setNombre(nombre);
        setTipo(tipo);
        setTelefono(telefono);
        setComensales(comensales);
        setEmail(email);
        setPassword(password);

    }
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Cliente: "+ getNombre() +
                " // id: " + getId_usuario() + "" +
                " // tipo: " + getTipo() + "" +
                " // telefono: " + getTelefono()+ "" +
                " // comensales: " + getComensales() + "" +
                " // email: " + getEmail() + "" +
                " // password: " + getPassword();
    }





}
