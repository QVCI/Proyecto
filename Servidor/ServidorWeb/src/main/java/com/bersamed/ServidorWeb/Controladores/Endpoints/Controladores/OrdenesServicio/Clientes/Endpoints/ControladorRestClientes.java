package com.bersamed.ServidorWeb.Controladores.Endpoints.Controladores.OrdenesServicio.Clientes.Endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios.SseServicio;
import com.bersamed.ServidorWeb.Seguridad.Tokens.RolesUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Clientes")
public class ControladorRestClientes 
{
    private final RolesUtil rolesUtil;
    private final SseServicio sseServicio;

    public ControladorRestClientes(RolesUtil rolesUtil, SseServicio sseServicio)
    {
        this.rolesUtil = rolesUtil;
        this.sseServicio = sseServicio;
    }
    @GetMapping("Ordenes/{id}")
    public ResponseEntity<String> getOrdenServicio(@PathVariable String id,
                                                   HttpServletRequest request) 
    {
        if (!rolesUtil.validarAcceso(request, Integer.parseInt(id)))  {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Sin permisos necesarios");
        }
        return ResponseEntity.ok("hola " + id);
    }
    
}
