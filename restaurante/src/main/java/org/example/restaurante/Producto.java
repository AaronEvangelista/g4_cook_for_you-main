package org.example.restaurante;

public class Producto {
    private int idProducto;
    private String nombre_producto;
    private int stock;

    public Producto(int idProducto, String nombre_producto, int stock) {
        setIdProducto(idProducto);
        setNombre_producto(nombre_producto);
        setStock(stock);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }




}
