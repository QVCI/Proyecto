package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bitacoraserviciomonitoreo")
@IdClass(BitacoraServicioMonitoreoId.class)
public class BitacoraServicioMonitoreoEntidad {
        @Id
    @Column(name = "bcodigo")
    private String bcodigo;

    @Id
    @Column(name = "bidversion")
    private short bidversion;

    @Id
    @Column(name = "dpartida")
    private String dpartida;

    @Id
    @Column(name = "idmensaje")
    private short idmensaje;
   
    @Column(name = "fecharegistro")
    private String fecharegistro;

    @Column(name = "horaregistro")
    private String horaregistro;

    @Column(name = "usuarioregistro")
    private String usuarioregistro;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bcodigo", referencedColumnName = "codigo", insertable = false, updatable = false)
    private BitacoraServicioEntidad bitacoraServicio;

    public String getBcodigo() {
        return bcodigo;
    }

    public void setBcodigo(String bcodigo) {
        this.bcodigo = bcodigo;
    }

    public short getBidversion() {
        return bidversion;
    }

    public void setBidversion(short bidversion) {
        this.bidversion = bidversion;
    }

    public String getDpartida() {
        return dpartida;
    }

    public void setDpartida(String dpartida) {
        this.dpartida = dpartida;
    }

    public short getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(short idmensaje) {
        this.idmensaje = idmensaje;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getHoraregistro() {
        return horaregistro;
    }

    public void setHoraregistro(String horaregistro) {
        this.horaregistro = horaregistro;
    }

    public String getUsuarioregistro() {
        return usuarioregistro;
    }

    public void setUsuarioregistro(String usuarioregistro) {
        this.usuarioregistro = usuarioregistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BitacoraServicioEntidad getBitacoraServicio() {
        return bitacoraServicio;
    }

    public void setBitacoraServicio(BitacoraServicioEntidad bitacoraServicio) {
        this.bitacoraServicio = bitacoraServicio;
    }

    
    

}

