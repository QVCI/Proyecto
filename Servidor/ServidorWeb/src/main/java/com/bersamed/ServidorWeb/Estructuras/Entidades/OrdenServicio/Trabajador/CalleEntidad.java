package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "calle")
public class CalleEntidad 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "idcalle")
    private int IdCalle;

    @Column(name = "calle")
    private String Calle;

    public int getIdCalle() {
        return IdCalle;
    }

    public void setIdCalle(int idCalle) {
        IdCalle = idCalle;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

}
