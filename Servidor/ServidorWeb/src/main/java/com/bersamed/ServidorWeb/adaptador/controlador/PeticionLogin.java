package com.bersamed.ServidorWeb.adaptador.controlador;

public class PeticionLogin {
  private String usuario;
    private String password;

    
    public PeticionLogin() {
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
