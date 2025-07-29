package com.bersamed.ServidorWeb.adaptador.controlador.servicio;

import org.springframework.stereotype.Service;

import com.bersamed.ServidorWeb.infraestructura.entidad.UsuarioEntidad;
import com.bersamed.ServidorWeb.infraestructura.entidad.repositorio.UsuarioRepositorio;
import com.bersamed.ServidorWeb.infraestructura.jwt.HashUtil;
import com.bersamed.ServidorWeb.infraestructura.jwt.JwtUtil;

@Service
public class AutenticacionServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final JwtUtil jwtUtil;

    public AutenticacionServicio(UsuarioRepositorio usuarioRepositorio, JwtUtil jwtUtil) 
    {
        this.usuarioRepositorio = usuarioRepositorio;
        this.jwtUtil = jwtUtil;
    }

    public String iniciarSesion(String login, String claveIngresada) 
    throws Exception 
    {
        
        UsuarioEntidad usuario = usuarioRepositorio.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuario no existente"));

        String claveBD = usuario.getClavesistema();

        if (claveBD == null || claveBD.trim().isEmpty()) {
            throw new RuntimeException("El usuario " + login + " no tiene una clave válida asignada. " +
                    "Por favor solicite a su administrador de sistemas que asigne una clave en la BD.");
        }
                    


      
        // Extraer la "sal" de la clave almacenada: los primeros 16 bytes 
       String sal = claveBD.substring(0, 16);
        // Calcula hash local con la sal extraída
        String hashLocal = HashUtil.calcularHash(claveIngresada, sal);
        System.out.println("Este es el hash: "+hashLocal + " y usan:" + hashLocal.length());

        if (!claveBD.equals(hashLocal)) 
        {
            throw new RuntimeException("Credenciales inválidas");
        }

        
        return jwtUtil.generarToken(usuario.getAbreviatura(), usuario.getNombre());
    }

 
}
