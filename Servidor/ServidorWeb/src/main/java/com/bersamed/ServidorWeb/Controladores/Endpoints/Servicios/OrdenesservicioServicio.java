package com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador.BitacoraServicio;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.Comentario;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.DatosServicio;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.DetallesServicio;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.Servicio;
import com.bersamed.ServidorWeb.Repositorios.BitacoraServicioRepositorio;

@Service
public class OrdenesservicioServicio 
{
    private final BitacoraServicioRepositorio servicioRepositorio;

    public OrdenesservicioServicio(BitacoraServicioRepositorio servicioRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
    }

   public List<Servicio> getAllServicios() {
        List<BitacoraServicio> servicios = servicioRepositorio.findAll();
        List<Servicio> responses = new ArrayList<>();

        for (BitacoraServicio s : servicios) {
            Servicio response = new Servicio();
            response.setIdServicio(String.valueOf(s.getIdbitservicio()));

            // DatosServicio
            DatosServicio datos = new DatosServicio();
            datos.setRazonSocial(s.getRazonSocial());
            datos.setHora(s.getHoraCompromiso());
            datos.setDireccion("Ubicación trabajador desde Android");
            datos.setDireccionServicio("Concatenado dirección servicio");

            Comentario comentario = new Comentario();
            comentario.setAutorDelComentario(s.getAutorComentario());
            comentario.setFalla(s.getFalla());
            datos.setComentario(comentario);
            response.setDatosServicio(datos);

            // DetallesServicio
            if (s.getDetalle() != null) {
                DetallesServicio detalles = new DetallesServicio();
                detalles.setNumeroDeOrden(s.getDetalle().getNumeroDeOrden());
                detalles.setNumeroDeServicio(s.getDetalle().getNumeroDeServicio());
                datos.setEstadoServicio(s.getDetalle().getEstadoServicio().toString()); // o "Activo"/"Inactivo"
                response.setDetallesServicio(detalles);
            }

            // MensajesMonitoreo
            List<MensajeMonitoreoResponse> mensajesResponse = new ArrayList<>();
            if (s.getMensajesMonitoreo() != null) {
                for (var m : s.getMensajesMonitoreo()) {
                    MensajeMonitoreoResponse msg = new MensajeMonitoreoResponse();
                    msg.setCodigoDelMensaje(m.getCodigoDelMensaje());
                    msg.setFechaRegistroDelMensaje(m.getFechaRegistro());
                    msg.setHoraRegistroDelMensaje(m.getHoraRegistro());
                    msg.setUsuarioQueRegistroElMensaje(m.getUsuarioRegistro());
                    msg.setMensajeDelProblema(m.getMensajeProblema());
                    mensajesResponse.add(msg);
                }
            }
            response.setMensajeMonitoreo(mensajesResponse);

            responses.add(response);
        }

        return responses;
    }
    
}
