package com.bersamed.ServidorWeb.Estructuras.Json.Login;

import com.fasterxml.jackson.annotation.JsonProperty;

//Estructura de petición para login
public class PeticionLoginClientes {
    @JsonProperty("RazonSocial")
    private String RazonSocial;

    @JsonProperty("RFC")
    private String rfc;

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

   
}