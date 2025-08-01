package com.bersamed.ServidorWeb.Seguridad.Configuraciones;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

public class RateLimiting 
{
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    //Establece la cantidad de peticiones que puede realizar el cliente en una ventana de tiempo
    private final int tokensDisponiblesPorVentana = 5;

    //Establece la cantidad de tokens que se podrán tener como máximo
    private final int  tokensPorUsuario = 5;

    //Establece el tiempo que tarda en recargar los tokens por ip
    private final int tiempoDeRecargaTokens = 1;

    //Establece la cantidad de tokens que se consumiran por cada petición
    private final int tokensConsumidosPorPeticion = 1;



    public boolean permitirPeticion(String ip) {
        Bucket bucket = buckets.computeIfAbsent(ip, k -> crearBucket());
        return bucket.tryConsume(tokensConsumidosPorPeticion);
    }

    private Bucket crearBucket() {
        return Bucket.builder()
            .addLimit(Bandwidth.classic(
                tokensPorUsuario,
                Refill.intervally(
                    tokensDisponiblesPorVentana,
                    Duration.ofMinutes(tiempoDeRecargaTokens)
                )
            ))
            .build();
    }
}

