package com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador;

//Estructura secundaría de DatosServicio
public class Comentario 
{
    private String AutorDelComentario;
    private String Falla;
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

    
}
