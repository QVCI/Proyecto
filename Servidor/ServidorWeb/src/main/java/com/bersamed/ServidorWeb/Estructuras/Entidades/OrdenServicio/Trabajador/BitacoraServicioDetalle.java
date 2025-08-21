package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bitacoraserviciodetalle")
public class BitacoraServicioDetalle {

    @Id
    @Column(name = "bcodigo")
    private String bcodigo;

    @Column(name = "partida")
    private String numeroDeServicio;

    @Column(name = "estado") // suponiendo que usas esta columna como boolean
    private Boolean estadoServicio;

    // Relación 1:1 con Servicio
    @OneToOne
    @JoinColumn(name = "bcodigo", referencedColumnName = "codigo")
    private BitacoraServicio servicio;

    public String getBcodigo() {
        return bcodigo;
    }

    public void setBcodigo(String bcodigo) {
        this.bcodigo = bcodigo;
    }

    public String getNumeroDeServicio() {
        return numeroDeServicio;
    }

    public void setNumeroDeServicio(String numeroDeServicio) {
        this.numeroDeServicio = numeroDeServicio;
    }

    public Boolean getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(Boolean estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public BitacoraServicio getServicio() {
        return servicio;
    }

    public void setServicio(BitacoraServicio servicio) {
        this.servicio = servicio;
    }

    // getters y setters
}
