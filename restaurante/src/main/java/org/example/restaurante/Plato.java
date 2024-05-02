package org.example.restaurante;

public class Plato {
    private int idPlato;
    private int idUsuario;
    private String nombre_plato;
    private double precio;
    private String alergenos;
    private String ingredientes;

    public Plato(int idPlato, int idUsuario, String nombre_plato, double precio, String alergenos, String ingredientes) {
        setIdPlato(idPlato);
        setIdUsuario(idUsuario);
        setNombre_plato(nombre_plato);
        setPrecio(precio);
        setAlergenos(alergenos);
        setIngredientes(ingredientes);
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String alergenos) {
        this.alergenos = alergenos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre_plato() {
        return nombre_plato;
    }

    public void setNombre_plato(String nombre_plato) {
        this.nombre_plato = nombre_plato;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }


}
