package com.cesde.epssaludtotal.Modelos;

public class MAdm {

    private String usuario;
    private String clave;

    public MAdm(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public MAdm() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
