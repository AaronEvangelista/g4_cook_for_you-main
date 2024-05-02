package org.example.usuario;

public class Camarero extends Usuario {



    private String turno;

    public Camarero(int id_usuario, String nombre, String tipo, int telefono, int comensales, String email, String password, String turno){
        super(id_usuario,nombre,tipo,telefono,comensales,email,password);
        setTurno(turno);

    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }





}
