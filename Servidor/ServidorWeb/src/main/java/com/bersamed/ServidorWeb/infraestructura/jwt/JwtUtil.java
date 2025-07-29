package com.bersamed.ServidorWeb.infraestructura.jwt;


import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bersamed.ServidorWeb.infraestructura.config.Configuraciones;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
    private final Key clave;

    public JwtUtil() {
        
        Configuraciones configuraciones = new Configuraciones();
        this.clave = Keys.hmacShaKeyFor(configuraciones.getCertificadoAutogenerado().getBytes());
    }

    public String generarToken(String abreviatura, String nombreUsuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + 86400000); // 24 horas

        return Jwts.builder()
                .setIssuer("servidorweb")
                .setSubject(nombreUsuario)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .setId(UUID.randomUUID().toString())
                .claim("name", abreviatura + " " + nombreUsuario)
                .signWith(clave, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validarToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(clave)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
