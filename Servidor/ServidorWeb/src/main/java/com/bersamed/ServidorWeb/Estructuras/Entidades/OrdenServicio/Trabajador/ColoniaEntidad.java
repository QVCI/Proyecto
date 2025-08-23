package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "colonia")
public class ColoniaEntidad 
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "idcolonia")
    private int IdColonia;

    @Column(name = "colonia")
    private String Colonia;

    public int getIdColonia() {
        return IdColonia;
    }

    public void setIdColonia(int idColonia) {
        IdColonia = idColonia;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    
}
