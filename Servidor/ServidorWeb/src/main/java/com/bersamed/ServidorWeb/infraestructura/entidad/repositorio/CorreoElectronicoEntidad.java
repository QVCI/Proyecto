package com.bersamed.ServidorWeb.infraestructura.entidad.repositorio;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "correoselectronicos")
public class CorreoElectronicoEntidad 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_correo")
    private Long idCorreo;

    @Column(name = "correoelectronico")
    private String correoElectronico;

    @ManyToMany(mappedBy = "correos")
    private List<ClienteEntidad> clientes;

    public Long getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(Long idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<ClienteEntidad> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteEntidad> clientes) {
        this.clientes = clientes;
    }

    

}
