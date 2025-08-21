package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bitacoraservicio")
public class BitacoraServicio 
{
    @Id
    @Column(name = "codigo")
    private String codigo; 

    @Column(name = "idbitservicio")
    private Integer idbitservicio;

    @Column(name = "razonsocial")
    private String razonSocial;

    @Column(name = "horacompromiso")
    private String horaCompromiso;

    @Column(name = "reporto")
    private String autorComentario;

    @Column(name = "falla")
    private String falla;

    // Relación 1:1 con Detalle
    @OneToOne(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BitacoraServicioDetalle detalle;

    // Relación 1:N con Monitoreo
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BitacoraServicioMonitoreo> mensajesMonitoreo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdbitservicio() {
        return idbitservicio;
    }

    public void setIdbitservicio(Integer idbitservicio) {
        this.idbitservicio = idbitservicio;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getHoraCompromiso() {
        return horaCompromiso;
    }

    public void setHoraCompromiso(String horaCompromiso) {
        this.horaCompromiso = horaCompromiso;
    }

    public String getAutorComentario() {
        return autorComentario;
    }

    public void setAutorComentario(String autorComentario) {
        this.autorComentario = autorComentario;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public BitacoraServicioDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(BitacoraServicioDetalle detalle) {
        this.detalle = detalle;
    }

    public List<BitacoraServicioMonitoreo> getMensajesMonitoreo() {
        return mensajesMonitoreo;
    }

    public void setMensajesMonitoreo(List<BitacoraServicioMonitoreo> mensajesMonitoreo) {
        this.mensajesMonitoreo = mensajesMonitoreo;
    }

    
}
