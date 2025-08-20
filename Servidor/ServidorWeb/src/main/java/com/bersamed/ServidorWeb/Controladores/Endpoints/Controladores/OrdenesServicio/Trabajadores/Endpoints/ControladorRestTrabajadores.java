package com.bersamed.ServidorWeb.Controladores.Endpoints.Controladores.OrdenesServicio.Trabajadores.Endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios.SseServicio;
import com.bersamed.ServidorWeb.Seguridad.Tokens.RolesUtil;

import jakarta.servlet.http.HttpServletRequest;

/*
 * Endpoints HTTP Request para trabajadores
 * HTTP Request Get:
 *      /OrdenesServicio/{id}
 *      Cargar las actualizaciones a Android
 * 
 *      /OrdenesServicio
 *      Cargar las actualizaciones a Web
 * 
 * 
 * HTTP Request Post:
 *      /OrdenesServicio/notificar
 *      Informar de Actualizaciones a SSE
 */
@RestController
@RequestMapping("/OrdenesServicio")
public class ControladorRestTrabajadores 
{
    private final RolesUtil rolesUtil;
    private final SseServicio sseServicio;

    public ControladorRestTrabajadores(RolesUtil rolesUtil, SseServicio sseServicio)
    {
        this.rolesUtil = rolesUtil;
        this.sseServicio = sseServicio;
    }

    @GetMapping("Ordenes/{id}")
    public ResponseEntity<String> getOrdenServicio(@PathVariable String id,
                                                   HttpServletRequest request) 
    {
        if (!rolesUtil.validarAcceso(request, "Trabajador")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Sin permisos necesarios");
        }
        return ResponseEntity.ok("hola " + id);
    }

    @GetMapping("Ordenes")
    public ResponseEntity<String> getOrdenesServicio(HttpServletRequest request) {
        if (!rolesUtil.validarAcceso(request, "Trabajador")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("No tienes permiso para este endpoint");
        }
        return ResponseEntity.ok("hola se enviara toda la info");
    }
    
    @PostMapping("/notificar")
    public ResponseEntity<String> notificarCambio(
        @RequestParam String idUsuario, 
        @RequestParam String idUsuarioCliente,
        HttpServletRequest request) {

         if (!rolesUtil.validarAcceso(request, "Trabajador")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("No tienes permiso para este endpoint");
        }
        // Notificar a Android
        sseServicio.NotificarAndroid(idUsuario);

        // Notifica a web
        sseServicio.NotificarWebTrabajador();

        // Notificar a web cliente
        sseServicio.NotificarWebCliente(idUsuarioCliente);

        return ResponseEntity.ok("Notificación enviada");
    }



   
    
}