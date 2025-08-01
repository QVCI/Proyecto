package com.bersamed.ServidorWeb.Estructuras.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeticionLoginClientes {
    @JsonProperty("CorreoElectronico")
    private String correoElectronico;

    @JsonProperty("RFC")
    private String rfc;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getRFC() {
        return rfc;
    }

    public void setRFC(String rfc) {
        this.rfc = rfc;
    }
}