package com.bersamed.ServidorWeb.Controladores.Endpoints.Controladores.OrdenesServicio.Clientes.SSE;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios.SseServicio;
import com.bersamed.ServidorWeb.Seguridad.Tokens.RolesUtil;

import jakarta.servlet.http.HttpServletRequest;




@RestController
@RequestMapping("/Clientes")
public class ControladorSseClientes 
{
    private final RolesUtil rolesUtil;
    private final SseServicio sseServicio;

    public ControladorSseClientes(RolesUtil rolesUtil, SseServicio sseServicio)
    {
        this.rolesUtil = rolesUtil;
        this.sseServicio = sseServicio;
    }

    
    @GetMapping("/SSE/{id}")
    public SseEmitter streamAndroid(@PathVariable String id, HttpServletRequest request) {
        if (!rolesUtil.validarAcceso(request, Integer.parseInt(id))) {
            SseEmitter emitter = new SseEmitter();
            try {
                emitter.send(SseEmitter.event().name("error").data("No tienes permiso"));
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
            emitter.complete();
            return emitter;
        }

        return sseServicio.RegistrarWebCliente(id);
    }

}
