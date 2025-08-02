package com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios;

import org.springframework.stereotype.Service;

import com.bersamed.ServidorWeb.Estructuras.Entidades.ClienteEntidad;
import com.bersamed.ServidorWeb.Estructuras.Entidades.TrabajadorEntidad;
import com.bersamed.ServidorWeb.Repositorios.ClienteRepositorio;
import com.bersamed.ServidorWeb.Repositorios.TrabajadorRepositorio;
import com.bersamed.ServidorWeb.Seguridad.Hashing.HashUtil;
import com.bersamed.ServidorWeb.Seguridad.Tokens.JwtUtil;


@Service
public class AutenticacionServicio {

    private final TrabajadorRepositorio trabajadorRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final JwtUtil jwtUtil;

    public AutenticacionServicio(TrabajadorRepositorio trabajadorRepositorio, ClienteRepositorio clienteRepositorio, JwtUtil jwtUtil) 
    {
        this.trabajadorRepositorio = trabajadorRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.jwtUtil = jwtUtil;
    }

    public String iniciarSesionTrabajador(String login, String claveIngresada) 
    throws Exception 
    {
        
        
        TrabajadorEntidad usuario = trabajadorRepositorio.findByLogin(login)
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
        
       

        if (!claveBD.equals(hashLocal)) 
        {
            throw new RuntimeException("Credenciales inválidas");
        }
        if(usuario.getAbreviatura().trim().isEmpty())
        {
            return jwtUtil.generarTokenTrabajador(usuario.getNombre(), usuario.getId_usuario());
        }

        

        
        return jwtUtil.generarTokenTrabajador(usuario.getAbreviatura(), usuario.getNombre(), usuario.getId_usuario());
        
       
    }

    public String iniciarSesionCliente(String razonsocial, String rfc) throws Exception 
    {

        ClienteEntidad usuario = clienteRepositorio.findByRazonSocial(razonsocial)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                

        String RFC = usuario.getRfc();
        
        if(RFC == null || RFC.trim().isEmpty())
        {
             throw new RuntimeException("El usuario " + razonsocial + " no tiene guardado un rfc asociado," + 
            " solicite apoyo de atención al cliente");
        }

        if(!RFC.equals(rfc))
        {
            throw new RuntimeException("Credenciales inválidas");
        }
              
        return jwtUtil.generarTokenCliente(usuario.getRazonsocial(), usuario.getIdcliente());
    }

        

 
}
