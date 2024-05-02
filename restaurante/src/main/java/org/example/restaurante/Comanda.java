package org.example.restaurante;


import java.time.LocalDateTime;

public class Comanda {
    private int id_comanda;
    private int id_mesa;
    private LocalDateTime hora_comanda;
    private int turno_comanda;

    public Comanda(int id_comanda, int id_mesa, LocalDateTime hora_comanda, int turno_comanda) {
        setId_comanda(id_comanda);
        setId_mesa(id_mesa);
        setHora_comanda(hora_comanda);
        setTurno_comanda(turno_comanda);
    }

    public int getTurno_comanda() {
        return turno_comanda;
    }

    public void setTurno_comanda(int turno_comanda) {
        this.turno_comanda = turno_comanda;
    }

    public LocalDateTime getHora_comanda() {
        return hora_comanda;
    }

    public void setHora_comanda(LocalDateTime hora_comanda) {
        this.hora_comanda = hora_comanda;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }


}
