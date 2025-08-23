package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudad")
public class CiudadEntidad
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "idciudad")
    private int IdCiudad;

    @Column(name = "abreviatura")
    private String Abreviatura;

    public int getIdCiudad() {
        return IdCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        IdCiudad = idCiudad;
    }

    public String getAbreviatura() {
        return Abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        Abreviatura = abreviatura;
    }

    
}
