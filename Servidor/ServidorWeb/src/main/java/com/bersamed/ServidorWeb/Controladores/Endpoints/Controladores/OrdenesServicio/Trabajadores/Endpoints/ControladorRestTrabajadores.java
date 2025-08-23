package com.bersamed.ServidorWeb.Controladores.Endpoints.Controladores.OrdenesServicio.Trabajadores.Endpoints;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios.SseServicio;
import com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios.ordenesservicioServicio;
import com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador.BitacoraServicioEntidad;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.Servicio;
import com.bersamed.ServidorWeb.Repositorios.BitacoraServicioRepositorio;
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
    private final ordenesservicioServicio ordenesServicioServicio;
     private final BitacoraServicioRepositorio bitacoraServicioRepositorio;

    public ControladorRestTrabajadores(RolesUtil rolesUtil, SseServicio sseServicio, ordenesservicioServicio ordenesServicioServicio, BitacoraServicioRepositorio bitacoraServicioRepositorio )
    {
        this.rolesUtil = rolesUtil;
        this.sseServicio = sseServicio;
        this.ordenesServicioServicio = ordenesServicioServicio;
        this.bitacoraServicioRepositorio = bitacoraServicioRepositorio; 
    }

    @GetMapping("Ordenes/{id}")
    public ResponseEntity<?> getOrdenServicio(@PathVariable String id,
                                                   HttpServletRequest request) 
    {
        /*if (!rolesUtil.validarAcceso(request, "Trabajador")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Sin permisos necesarios");
        }
        if(!rolesUtil.obtenerId(request).equals(id) )
        {*/
          
        /* }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                            .body("No se puede acceder");
                                            */
        List<Servicio> servicios = ordenesServicioServicio.getServiciosPorTrabajador(Long.valueOf(id));
        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(servicios);                           
        
    }

    @GetMapping("Ordenes")
    public ResponseEntity<?> getOrdenesServicio(HttpServletRequest request) 
    {
        /* 
        if (!rolesUtil.validarAcceso(request, "Trabajador")) 
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                .body("No tienes permiso para este endpoint");
        }*/
        
        List<Servicio> servicios = ordenesServicioServicio.getAllServicios();
        if (servicios.isEmpty()) 
        {
            return ResponseEntity.noContent().build(); 
        }
        
        return ResponseEntity.ok(servicios);
    }

    
    @PostMapping("/notificar")
    public ResponseEntity<String> notificarCambio(
        @RequestParam String idUsuario, 
        @RequestParam String idUsuarioCliente,
        HttpServletRequest request) {

         if (rolesUtil.validarAcceso(request, "Cliente")) {
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