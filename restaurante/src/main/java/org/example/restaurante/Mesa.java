package org.example.restaurante;

public class Mesa {
    private int idMesa;
    private int idUsuario;
    private boolean estado;
    private int capacidad;

    public Mesa(int idMesa, int idUsuario, boolean estado, int capacidad) {
        setIdMesa(idMesa);
        setIdUsuario(idUsuario);
        setEstado(estado);
        setCapacidad(capacidad);
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }


}
