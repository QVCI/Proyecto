package com.bersamed.ServidorWeb.infraestructura.entidad.repositorio;
import jakarta.persistence.*;

@Entity
@Table(name = "clientecorreo")
public class ClienteCorreoEntidad 
{
    @EmbeddedId
    private ClienteCorreoId id;

    @ManyToOne
    @MapsId("idcliente")
    @JoinColumn(name = "idcliente")
    private ClienteEntidad cliente;

    @ManyToOne
    @MapsId("correo")
    @JoinColumn(name = "correo")
    private CorreoElectronicoEntidad correo;


}
