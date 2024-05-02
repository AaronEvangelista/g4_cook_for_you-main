package org.example.usuario;

public class Cocinero extends Usuario {
    private String turno;
    private String especialidad;

    public Cocinero(int id_usuario, String nombre, String tipo, int telefono, int comensales, String email, String password, String turno, String especialidad){
        super(id_usuario,nombre,tipo,telefono,comensales, email, password);
        setTurno(turno);
        setEspecialidad(especialidad);
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }



}
