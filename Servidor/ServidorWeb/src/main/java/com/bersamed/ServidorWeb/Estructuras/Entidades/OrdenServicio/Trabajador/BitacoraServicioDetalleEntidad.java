package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bitacoraserviciodetalle")
@IdClass(BitacoraServicioDetalleId.class)
public class BitacoraServicioDetalleEntidad {

    @Id
    @Column(name = "bcodigo")
    private String bcodigo;

    @Id
    @Column(name = "bidversion")
    private short bidversion;

    @Id
    @Column(name = "partida")
    private String partida;

    @Id
    @Column(name = "idversion")
    private short idversion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bcodigo", referencedColumnName = "codigo", insertable = false, updatable = false)
    private BitacoraServicioEntidad bitacoraServicio;

    @Column(name = "conautomovil")
    private boolean conautomovil;

    @Column(name = "conentransito")
    private boolean conentransito;

    @Column(name = "conatendiendo")
    private boolean conatendiendo;

    @Column(name = "conresuelto")
    private boolean conresuelto;

    @Column(name = "connoresuelto")
    private boolean connoresuelto;

    @Column(name = "conretorno")
    private boolean conretorno;

    @Column(name = "conparo")
    private boolean conparo;

    @Column(name = "contermino")
    private boolean contermino;

    @Column(name = "convisitaprogramada")
    private boolean convisitaprogramada;

    @Column(name = "coninicio")
    private boolean coninicio;

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

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public short getIdversion() {
        return idversion;
    }

    public void setIdversion(short idversion) {
        this.idversion = idversion;
    }

    public BitacoraServicioEntidad getBitacoraServicio() {
        return bitacoraServicio;
    }

    public void setBitacoraServicio(BitacoraServicioEntidad bitacoraServicio) {
        this.bitacoraServicio = bitacoraServicio;
    }

    public boolean isConautomovil() {
        return conautomovil;
    }

    public void setConautomovil(boolean conautomovil) {
        this.conautomovil = conautomovil;
    }

    public boolean isConentransito() {
        return conentransito;
    }

    public void setConentransito(boolean conentransito) {
        this.conentransito = conentransito;
    }

    public boolean isConatendiendo() {
        return conatendiendo;
    }

    public void setConatendiendo(boolean conatendiendo) {
        this.conatendiendo = conatendiendo;
    }

    public boolean isConresuelto() {
        return conresuelto;
    }

    public void setConresuelto(boolean conresuelto) {
        this.conresuelto = conresuelto;
    }

    public boolean isConnoresuelto() {
        return connoresuelto;
    }

    public void setConnoresuelto(boolean connoresuelto) {
        this.connoresuelto = connoresuelto;
    }

    public boolean isConretorno() {
        return conretorno;
    }

    public void setConretorno(boolean conretorno) {
        this.conretorno = conretorno;
    }

    public boolean isConparo() {
        return conparo;
    }

    public void setConparo(boolean conparo) {
        this.conparo = conparo;
    }

    public boolean isContermino() {
        return contermino;
    }

    public void setContermino(boolean contermino) {
        this.contermino = contermino;
    }

    public boolean isConvisitaprogramada() {
        return convisitaprogramada;
    }

    public void setConvisitaprogramada(boolean convisitaprogramada) {
        this.convisitaprogramada = convisitaprogramada;
    }

    public boolean isConinicio() {
        return coninicio;
    }

    public void setConinicio(boolean coninicio) {
        this.coninicio = coninicio;
    }

    
}
