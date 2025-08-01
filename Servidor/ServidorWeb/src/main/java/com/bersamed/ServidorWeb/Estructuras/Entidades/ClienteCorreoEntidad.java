package com.bersamed.ServidorWeb.Estructuras.Entidades;
import com.bersamed.ServidorWeb.Estructuras.Entidades.SubEntidades.ClienteCorreoId;

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
