package com.bersamed.ServidorWeb.Estructuras.Json;

public class PeticionLoginTrabajadores {
    private String usuario;
    private String password;

    
    public PeticionLoginTrabajadores() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
