package com.bersamed.ServidorWeb.Seguridad.Tokens;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;



@Component
public class RolesUtil {

    private final JwtUtil jwtUtil;

    public RolesUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String obtenerRol(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.validarToken(token);
            return claims.get("rol", String.class);
        }
        return null;
    }

    public String obtenerId(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.validarToken(token);
            return claims.get("sub", String.class);
        }
        return null;
    }
    public boolean validarAcceso(HttpServletRequest request, int IdCliente) {
        int IdToken = Integer.parseInt(obtenerId(request));
        return IdCliente == IdToken;
    }

    public boolean validarAcceso(HttpServletRequest request, String rolPermitido) {
        String rolUsuario = obtenerRol(request);
        return rolPermitido.equals(rolUsuario);
    }
}
