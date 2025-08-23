package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "poblacion")
public class PoblacionEntidad 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "idpoblacion")
    private int IdPoblacion;

    @Column(name = "poblacion1")
    private String Poblacion1;

    public int getIdPoblacion() {
        return IdPoblacion;
    }

    public void setIdPoblacion(int idPoblacion) {
        IdPoblacion = idPoblacion;
    }

    public String getPoblacion1() {
        return Poblacion1;
    }

    public void setPoblacion1(String poblacion1) {
        Poblacion1 = poblacion1;
    }

    

}
