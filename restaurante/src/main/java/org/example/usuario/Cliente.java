package org.example.usuario;

public class Cliente extends Usuario {
    private int comensales;
    private String alergias;









    public Cliente(int id_usuario, String nombre, String tipo, int comensales,int telefono, String alergias, String email, String password) {
        super(id_usuario,nombre,tipo, telefono,comensales,email, password);
        setAlergias(alergias);
        setComensales(comensales);



    }


    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }



    @Override
    public String toString() {
        return "Cliente: "+ getNombre() +
                " // id: " + getId_usuario() + "" +
                " // tipo: " + getTipo() + "" +
                " // telefono: " + getTelefono() + "" +
                " // comensales: "+ getComensales() +"" +
                " // alergia: " + getAlergias() + "" +
                " // email: " + getEmail() + "" +
                " // password: " + getPassword() + "]";
    }




}
