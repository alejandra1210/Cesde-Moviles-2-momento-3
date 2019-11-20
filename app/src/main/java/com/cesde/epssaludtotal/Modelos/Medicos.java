package com.cesde.epssaludtotal.Modelos;

public class Medicos {

    //mdeclarar todas las variables que utilizaresmos en la base de datos

    private String cedula;
    private String nombre ;
    private String correo;
    private String especialidad;
    private String clave;

    public Medicos() { //contructor vacio
    }

    public Medicos(String cedula, String nombre, String correo, String especialidad, String clave) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.especialidad = especialidad;
        this.clave = clave;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
