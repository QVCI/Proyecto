package com.bersamed.ServidorWeb.Controladores.Endpoints.Controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios.AutenticacionServicio;
import com.bersamed.ServidorWeb.Estructuras.Json.PeticionLoginClientes;
import com.bersamed.ServidorWeb.Estructuras.Json.PeticionLoginTrabajadores;
import com.bersamed.ServidorWeb.Seguridad.Configuraciones.RateLimiting;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class ControladorAuth {

    private final AutenticacionServicio autenticacionServicio;
    private final RateLimiting rateLimiting = new RateLimiting();

    public ControladorAuth(AutenticacionServicio autenticacionServicio) {
        this.autenticacionServicio = autenticacionServicio;
    }

    @PostMapping("/trabajadores")
    public ResponseEntity<String> loginTrabajadores(@RequestBody PeticionLoginTrabajadores peticion, HttpServletRequest request) throws Exception {
        String ip = request.getRemoteAddr();

        if (!rateLimiting.permitirPeticion(ip)) {
            return ResponseEntity.status(429).body("Demasiados intentos. Intenta de nuevo en un momento.");
        }

        try {
            String token = autenticacionServicio.iniciarSesionTrabajador(peticion.getUsuario(), peticion.getPassword());
            return ResponseEntity.ok(token);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
      @PostMapping("/clientes")
    public ResponseEntity<String> loginClientes(@RequestBody PeticionLoginClientes peticion, HttpServletRequest request) throws Exception {
        String ip = request.getRemoteAddr();

        if (!rateLimiting.permitirPeticion(ip)) {
            return ResponseEntity.status(429).body("Demasiados intentos. Intenta de nuevo en un momento.");
        }
       
        try {
            String token = autenticacionServicio.iniciarSesionCliente(peticion.getCorreoElectronico(), peticion.getRFC());
            return ResponseEntity.ok(token);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
