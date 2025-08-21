package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;


import jakarta.persistence.*;

@Entity
@Table(name = "bitacoraserviciomonitoreo")
public class BitacoraServicioMonitoreo {

    @Id
    @Column(name = "bcodigo")
    private String bcodigo;

    @Column(name = "fecharegistro")
    private String fechaRegistro;

    @Column(name = "horaregistro")
    private String horaRegistro;

    @Column(name = "usuarioregistro")
    private String usuarioRegistro;

    @Column(name = "descripcion")
    private String mensajeProblema;

    // Relación N:1 con Servicio
    @ManyToOne
    @JoinColumn(name = "bcodigo", referencedColumnName = "codigo", insertable = false, updatable = false)
    private BitacoraServicio servicio;

    public String getBcodigo() {
        return bcodigo;
    }

    public void setBcodigo(String bcodigo) {
        this.bcodigo = bcodigo;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(String horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getMensajeProblema() {
        return mensajeProblema;
    }

    public void setMensajeProblema(String mensajeProblema) {
        this.mensajeProblema = mensajeProblema;
    }

    public BitacoraServicio getServicio() {
        return servicio;
    }

    public void setServicio(BitacoraServicio servicio) {
        this.servicio = servicio;
    }

    // getters y setters
}
