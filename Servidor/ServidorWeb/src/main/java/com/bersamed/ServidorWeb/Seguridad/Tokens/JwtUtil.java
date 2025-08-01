package com.bersamed.ServidorWeb.Seguridad.Tokens;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Value("${configuraciones.jwt.certificadoAutogenerado}")
    private String certificadoAutogenerado;
    
    

    private Key clave;

  @PostConstruct
    public void init() 
    {
        this.clave = Keys.hmacShaKeyFor(certificadoAutogenerado.getBytes());
    }


    //Genera token para trabajadores con abreviatura y nombre
    public String generarTokenTrabajador(String abreviatura, String nombreUsuario, Long id_Usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + 86400000); // 24 horas

        return Jwts.builder()
                .setIssuer("servidorweb")
                .setSubject(String.valueOf(id_Usuario))
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .setId(UUID.randomUUID().toString())
                .claim("nombre", abreviatura + " " + nombreUsuario)
                .claim("rol", "Trabajador")
                .signWith(clave, SignatureAlgorithm.HS256)
                .compact();
    }


    //Genera token para trabajadores con nombre
    public String generarTokenTrabajador(String nombreUsuario, Long id_Usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + 86400000); // 24 horas

        return Jwts.builder()
                .setIssuer("servidorweb")
                .setSubject(String.valueOf(id_Usuario))
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .setId(UUID.randomUUID().toString())
                .claim("nombre", nombreUsuario)
                .claim("rol", "Trabajador")
                .signWith(clave, SignatureAlgorithm.HS256)
                .compact();
    }


    //Genera token para clientes con razón social
   public String generarTokenCliente(String razonsocial, Long id_Usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + 86400000); // 24 horas

        return Jwts.builder()
                .setIssuer("servidorweb")
                .setSubject(String.valueOf(id_Usuario))
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .setId(UUID.randomUUID().toString())
                .claim("nombre", razonsocial)
                .claim("rol", "Cliente")
                .signWith(clave, SignatureAlgorithm.HS256)
                .compact();
    }


    //Valida la integridad del token
    public Claims validarToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(clave)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
   
}
