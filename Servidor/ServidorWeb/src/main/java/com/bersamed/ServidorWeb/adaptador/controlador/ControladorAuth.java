package com.bersamed.ServidorWeb.adaptador.controlador;


import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bersamed.ServidorWeb.infraestructura.config.Configuraciones;
import com.bersamed.ServidorWeb.infraestructura.jwt.JwtUtil;
import com.bersamed.ServidorWeb.infraestructura.jwt.Sha1Util;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;





@RestController


@RequestMapping("/auth")
public class ControladorAuth {

    Configuraciones configuraciones = new Configuraciones();


    private final JwtUtil jwtUtil = new JwtUtil();
    private final Map<String, Bucket> rateLimiters = new ConcurrentHashMap<>();

    private Bucket createNewBucket() 
    {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(configuraciones.getCantidadPeticionesSimultaneo(), 
                Refill.intervally(configuraciones.getCantidadTokens(), 
                Duration.ofMinutes(configuraciones.getTiempoRecargaTokens())))) 
                .build();
                 //limitar intentos a 5 x cada minuto para info más a detalle ver Configuraciones.java
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody PeticionLogin peticion, HttpServletRequest request) {
        String usuario = peticion.getUsuario();
        String password = peticion.getPassword();

        String ip = request.getRemoteAddr();
        Bucket bucket = rateLimiters.computeIfAbsent(ip, k -> createNewBucket());

        if (!bucket.tryConsume(1)) {
            return ResponseEntity.status(429).body("Demasiados intentos. Intenta de nuevo en un momento.");
        }

        String passwordHasheado = Sha1Util.hashSha1(password);

        if (usuario.equals("admin") && passwordHasheado.equals(Sha1Util.hashSha1("1234"))) {
            String token = jwtUtil.generarToken("Carlo");
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

}
