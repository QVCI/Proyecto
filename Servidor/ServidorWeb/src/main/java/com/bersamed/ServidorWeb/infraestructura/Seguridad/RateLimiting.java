package com.bersamed.ServidorWeb.infraestructura.Seguridad;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bersamed.ServidorWeb.infraestructura.config.Configuraciones;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

public class RateLimiting {
    
    private final Configuraciones configuraciones = new Configuraciones();
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public boolean permitirPeticion(String ip) {
        Bucket bucket = buckets.computeIfAbsent(ip, k -> crearBucket());
        return bucket.tryConsume(1);
    }

    private Bucket crearBucket() {
        return Bucket.builder()
            .addLimit(Bandwidth.classic(
                configuraciones.getCantidadPeticionesSimultaneo(),
                Refill.intervally(
                    configuraciones.getCantidadTokens(),
                    Duration.ofMinutes(configuraciones.getTiempoRecargaTokens())
                )
            ))
            .build();
    }
}

