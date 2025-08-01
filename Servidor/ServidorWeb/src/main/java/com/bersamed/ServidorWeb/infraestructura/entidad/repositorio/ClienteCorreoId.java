package com.bersamed.ServidorWeb.infraestructura.entidad.repositorio;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ClienteCorreoId implements Serializable {

    private Long idcliente;
    private Long correo;

    public ClienteCorreoId() {}

    public ClienteCorreoId(Long idcliente, Long correo) {
        this.idcliente = idcliente;
        this.correo = correo;
    }

    

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Long getCorreo() {
        return correo;
    }

    public void setCorreo(Long correo) {
        this.correo = correo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteCorreoId)) return false;
        ClienteCorreoId that = (ClienteCorreoId) o;
        return Objects.equals(idcliente, that.idcliente) &&
               Objects.equals(correo, that.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcliente, correo);
    }
}
