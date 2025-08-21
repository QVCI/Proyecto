package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bitacoraservicio")
public class BitacoraServicioEntidad 
{
    @Id
    @Column(name = "codigo")
    private String Codigo;

    @Column(name = "idbitservicio")
    private short IdServicio;

    @Column(name = "razonsocial")
    private String RazonSocial;

    @Column(name = "horacompromiso")
    private String Hora;

    @Column(name = "reporto")
    private String AutorDelComentario;

    @Column(name = "falla")
    private String Falla;

    //Obtener Direccion

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idciudad", referencedColumnName = "idciudad")
    private CiudadEntidad CiudadEntidad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpoblacion", referencedColumnName = "idpoblacion")
    private PoblacionEntidad PoblacionEntidad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcalle", referencedColumnName = "idcalle")
    private CalleEntidad CalleEntidad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcolonia", referencedColumnName = "idcolonia")
    private ColoniaEntidad ColoniaEntidad;

    @Column(name = "numeroexterior")
    private String NumeroExterior;

    @Column(name = "numerointerior")
    private String NumeroInterior;

    //Relacion Con Detalle
    @OneToOne(mappedBy = "bitacoraServicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BitacoraServicioDetalleEntidad bitacoraServicioDetalleEntidad;

    //Relacion con Monitoreo
    @OneToMany(mappedBy = "bitacoraServicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BitacoraServicioMonitoreoEntidad> bitacoraServicioMonitoreoEntidad;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public short getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(short idServicio) {
        IdServicio = idServicio;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getAutorDelComentario() {
        return AutorDelComentario;
    }

    public void setAutorDelComentario(String autorDelComentario) {
        AutorDelComentario = autorDelComentario;
    }

    public String getFalla() {
        return Falla;
    }

    public void setFalla(String falla) {
        Falla = falla;
    }

    public CiudadEntidad getCiudadEntidad() {
        return CiudadEntidad;
    }

    public void setCiudadEntidad(CiudadEntidad ciudadEntidad) {
        CiudadEntidad = ciudadEntidad;
    }

    public PoblacionEntidad getPoblacionEntidad() {
        return PoblacionEntidad;
    }

    public void setPoblacionEntidad(PoblacionEntidad poblacionEntidad) {
        PoblacionEntidad = poblacionEntidad;
    }

    public CalleEntidad getCalleEntidad() {
        return CalleEntidad;
    }

    public void setCalleEntidad(CalleEntidad calleEntidad) {
        CalleEntidad = calleEntidad;
    }

    public ColoniaEntidad getColoniaEntidad() {
        return ColoniaEntidad;
    }

    public void setColoniaEntidad(ColoniaEntidad coloniaEntidad) {
        ColoniaEntidad = coloniaEntidad;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        NumeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        NumeroInterior = numeroInterior;
    }

    public BitacoraServicioDetalleEntidad getBitacoraServicioDetalleEntidad() {
        return bitacoraServicioDetalleEntidad;
    }

    public void setBitacoraServicioDetalleEntidad(BitacoraServicioDetalleEntidad bitacoraServicioDetalleEntidad) {
        this.bitacoraServicioDetalleEntidad = bitacoraServicioDetalleEntidad;
    }

    public List<BitacoraServicioMonitoreoEntidad> getBitacoraServicioMonitoreoEntidad() {
        return bitacoraServicioMonitoreoEntidad;
    }

    public void setBitacoraServicioMonitoreoEntidad(
            List<BitacoraServicioMonitoreoEntidad> bitacoraServicioMonitoreoEntidad) {
        this.bitacoraServicioMonitoreoEntidad = bitacoraServicioMonitoreoEntidad;
    }
    
     public String getDireccionCompleta() 
    {
    StringBuilder direccion = new StringBuilder();

    if (CalleEntidad != null) {
        direccion.append(CalleEntidad.getCalle());
    }

    if (NumeroExterior != null && !NumeroExterior.isEmpty()) {
        direccion.append(" ").append(NumeroExterior);
    }

    if (NumeroInterior != null && !NumeroInterior.isEmpty()) {
        direccion.append(", Int. ").append(NumeroInterior);
    }

    if (ColoniaEntidad != null) {
        direccion.append(", Col. ").append(ColoniaEntidad.getColonia());
    }

    if (PoblacionEntidad != null) {
        direccion.append(", ").append(PoblacionEntidad.getPoblacion1());
    }

    if (CiudadEntidad != null) {
        direccion.append(", ").append(CiudadEntidad.getAbreviatura()).append(".");
    }

    return direccion.toString();
    }


    
    
}
