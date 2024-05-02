package org.example.restaurante;

public class    Menu {
    private int idMenu;
    private String nombre;
    private double precio;

    public Menu(int idMenu, String nombre, double precio) {
        setIdMenu(idMenu);
        setNombre(nombre);
        setPrecio(precio);
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }


}
