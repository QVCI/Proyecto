package com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador;

//Estructura secundaría de Orden Servicio
public class DetallesServicio 
{
    private String NumeroDeOrden;
    private String NumeroDeServicio;
    private String NombreDelTrabajadorResponsable;
    private int IdDelTrabajadorResponsable;
    public String getNumeroDeOrden() {
        return NumeroDeOrden;
    }
    public void setNumeroDeOrden(String numeroDeOrden) {
        NumeroDeOrden = numeroDeOrden;
    }
    public String getNumeroDeServicio() {
        return NumeroDeServicio;
    }
    public void setNumeroDeServicio(String numeroDeServicio) {
        NumeroDeServicio = numeroDeServicio;
    }
    public String getNombreDelTrabajadorResponsable() {
        return NombreDelTrabajadorResponsable;
    }
    public void setNombreDelTrabajadorResponsable(String nombreDelTrabajadorResponsable) {
        NombreDelTrabajadorResponsable = nombreDelTrabajadorResponsable;
    }
    public int getIdDelTrabajadorResponsable() {
        return IdDelTrabajadorResponsable;
    }
    public void setIdDelTrabajadorResponsable(int idDelTrabajadorResponsable) {
        IdDelTrabajadorResponsable = idDelTrabajadorResponsable;
    }
    
    
}
