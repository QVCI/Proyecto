package com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
/*
 * Endpoints SSE 
 *
 */
@Component
public class SseServicio 
{
    //Lista de Android Conectados a SSE
    private final Map<String, SseEmitter> androidActivos = new ConcurrentHashMap<>();

    //Lista de Web - Clientes Conectados a SSE
    private final Map<String, SseEmitter> webClientesActivos = new ConcurrentHashMap<>();
    
    //Lista de Web - Trabajadores Conectados a SSE
    private final List<SseEmitter> webActivos = new CopyOnWriteArrayList<>();

    // Registrar Android conectado
    public SseEmitter RegistrarAndroid(String id) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        androidActivos.put(id, emitter);

        emitter.onCompletion(() -> androidActivos.remove(id));
        emitter.onTimeout(() -> androidActivos.remove(id));

        return emitter;
    }

    // Registrar Web - Cliente conectado
    public SseEmitter RegistrarWebCliente(String IdUsuario) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        webClientesActivos.put(IdUsuario, emitter);

        emitter.onCompletion(() -> webClientesActivos.remove(IdUsuario));
        emitter.onTimeout(() -> webClientesActivos.remove(IdUsuario));


        return emitter;
    }


    // Registrar Web - Trabajador conectado
    public SseEmitter RegistrarWebTrabajador() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        webActivos.add(emitter);

        emitter.onCompletion(() -> webActivos.remove(emitter));
        emitter.onTimeout(() -> webActivos.remove(emitter));

        return emitter;
    }

   // Notificar a un Android específico
    public void NotificarAndroid(String id) {
        SseEmitter emitter = androidActivos.get(id);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                                    .name("Actualizacion"));
            } catch (IOException e) {
                emitter.completeWithError(e);
                androidActivos.remove(id);
            }
        }
    }

    // Notificar a un cliente web
    public void NotificarWebCliente(String id) {
        SseEmitter emitter = webClientesActivos.get(id);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                                    .name("Actualizacion"));
            } catch (IOException e) {
                emitter.completeWithError(e);
                webClientesActivos.remove(id);
            }
        }
    }

    // Notificar a todos los webs
    public void NotificarWebTrabajador() {
        for (SseEmitter emitter : webActivos) {
            try {
                emitter.send(SseEmitter.event()
                                    .name("Actualizacion"));
            } catch (IOException e) {
                emitter.completeWithError(e);
                webActivos.remove(emitter);
            }
        }
    }
}