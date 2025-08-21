package com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador;

import java.util.List;

//Estructura Principal
public class Servicio 
{
    private String IdServicio;
    private DatosServicio DatosServicio;
    private DetallesServicio DetallesServicio;
    private List<MensajeMonitoreo> MensajeMonitoreo;

    public String getIdServicio() {
        return IdServicio;
    }
    public void setIdServicio(String idServicio) {
        IdServicio = idServicio;
    }
    public DatosServicio getDatosServicio() {
        return DatosServicio;
    }
    public void setDatosServicio(DatosServicio datosServicio) {
        DatosServicio = datosServicio;
    }
    public DetallesServicio getDetallesServicio() {
        return DetallesServicio;
    }
    public void setDetallesServicio(DetallesServicio detallesServicio) {
        DetallesServicio = detallesServicio;
    }
    public List<MensajeMonitoreo> getMensajeMonitoreo() {
        return MensajeMonitoreo;
    }
    public void setMensajeMonitoreo(List<MensajeMonitoreo> mensajeMonitoreo) {
        MensajeMonitoreo = mensajeMonitoreo;
    }    
}
