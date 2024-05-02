package org.example.usuario;

public class Administrador extends Usuario {
    private String turno;
    private double cobro_comanda;

    public Administrador(int id_usuario, String nombre, String tipo, int telefono,int comensales, String email, String password, String turno, double cobro_comanda){
        super(id_usuario,nombre,tipo, telefono,comensales,email,password);
        setTurno(turno);
        setCobro_comanda(cobro_comanda);
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public double getCobro_comanda() {
        return cobro_comanda;
    }

    public void setCobro_comanda(double cobro_comanda) {
        this.cobro_comanda = cobro_comanda;
    }


}
