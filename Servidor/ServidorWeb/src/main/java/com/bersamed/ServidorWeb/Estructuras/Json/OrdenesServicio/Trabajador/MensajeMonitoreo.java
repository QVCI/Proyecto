package com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador;

//Estructura secundaría de Orden Servicio
public class MensajeMonitoreo
{
    private String CodigoMensaje;
    private String FechaRegistroDelMensaje;
    private String HoraRegistroDelMensaje;
    private String UsuarioQueRegistroElMensaje;
    private String MensajeDelProblema;

    public String getCodigoMensaje() {
        return CodigoMensaje;
    }
    public void setCodigoMensaje(String codigoMensaje) {
        CodigoMensaje = codigoMensaje;
    }
    public String getFechaRegistroDelMensaje() {
        return FechaRegistroDelMensaje;
    }
    public void setFechaRegistroDelMensaje(String fechaRegistroDelMensaje) {
        FechaRegistroDelMensaje = fechaRegistroDelMensaje;
    }
    public String getHoraRegistroDelMensaje() {
        return HoraRegistroDelMensaje;
    }
    public void setHoraRegistroDelMensaje(String horaRegistroDelMensaje) {
        HoraRegistroDelMensaje = horaRegistroDelMensaje;
    }
    public String getUsuarioQueRegistroElMensaje() {
        return UsuarioQueRegistroElMensaje;
    }
    public void setUsuarioQueRegistroElMensaje(String usuarioQueRegistroElMensaje) {
        UsuarioQueRegistroElMensaje = usuarioQueRegistroElMensaje;
    }
    public String getMensajeDelProblema() {
        return MensajeDelProblema;
    }
    public void setMensajeDelProblema(String mensajeDelProblema) {
        MensajeDelProblema = mensajeDelProblema;
    }
}
