package com.bersamed.ServidorWeb.infraestructura.entidad.repositorio;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteEntidad 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcliente;

    private String rfc;

    @Column(name = "razonsocial")
    private String razonSocial;


    @ManyToMany
    @JoinTable(
        name = "clientecorreo",
        joinColumns = @JoinColumn(name = "idcliente"),
        inverseJoinColumns = @JoinColumn(name = "correo")
    )
    private List<CorreoElectronicoEntidad> correos;

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public List<CorreoElectronicoEntidad> getCorreos() {
        return correos;
    }

    public void setCorreos(List<CorreoElectronicoEntidad> correos) {
        this.correos = correos;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
}
