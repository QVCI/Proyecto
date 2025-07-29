package com.bersamed.ServidorWeb.adaptador.controlador;


import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bersamed.ServidorWeb.adaptador.controlador.servicio.AutenticacionServicio;
import com.bersamed.ServidorWeb.infraestructura.config.Configuraciones;
import com.bersamed.ServidorWeb.infraestructura.jwt.HashUtil;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/auth")
public class ControladorAuth 
{
    

    
    private final AutenticacionServicio autenticacionServicio;
    private final Configuraciones configuraciones = new Configuraciones();
    private final Map<String, Bucket> rateLimiters = new ConcurrentHashMap<>();
    

    public ControladorAuth(AutenticacionServicio autenticacionServicio) {
        this.autenticacionServicio = autenticacionServicio;
    }

    private Bucket createNewBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                    configuraciones.getCantidadPeticionesSimultaneo(), 
                    Refill.intervally(
                        configuraciones.getCantidadTokens(), 
                        Duration.ofMinutes(configuraciones.getTiempoRecargaTokens()))
                ))
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody PeticionLogin peticion, HttpServletRequest request) throws Exception 
    {   
  
        String usuario = peticion.getUsuario();
        String password = peticion.getPassword();

        String ip = request.getRemoteAddr();
        Bucket bucket = rateLimiters.computeIfAbsent(ip, k -> createNewBucket());

        if (!bucket.tryConsume(1)) {
            return ResponseEntity.status(429).body("Demasiados intentos. Intenta de nuevo en un momento.");
        }

        try 
        {
            String token = autenticacionServicio.iniciarSesion(usuario, password);
            return ResponseEntity.ok(token);
        } 
        //Excepciones controladas
        catch (RuntimeException ex) 
        {
            return ResponseEntity.status(401).body(ex.getMessage());
        } 
        //Excepciones internas
        catch (Exception e) 
        {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
