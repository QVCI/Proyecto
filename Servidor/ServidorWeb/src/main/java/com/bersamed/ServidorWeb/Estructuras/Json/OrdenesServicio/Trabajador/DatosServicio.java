package com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador;

//Estructura secundaría de Orden Servicio
public class DatosServicio 
{
    private String RazonSocial;
    private String Hora;
    private String EstadoServicio;
    private String Direccion;
    private String DireccionServicio;
    private Comentario Comentario;

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
    public String getEstadoServicio() {
        return EstadoServicio;
    }
    public void setEstadoServicio(String estadoServicio) {
        EstadoServicio = estadoServicio;
    }
    public String getDireccion() {
        return Direccion;
    }
    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
    public String getDireccionServicio() {
        return DireccionServicio;
    }
    public void setDireccionServicio(String direccionServicio) {
        DireccionServicio = direccionServicio;
    }
    public Comentario getComentario() {
        return Comentario;
    }
    public void setComentario(Comentario comentario) {
        Comentario = comentario;
    }

    
}
