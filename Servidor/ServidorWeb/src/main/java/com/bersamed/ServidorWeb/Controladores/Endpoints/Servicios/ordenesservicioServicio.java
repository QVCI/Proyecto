package com.bersamed.ServidorWeb.Controladores.Endpoints.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador.BitacoraServicioEntidad;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.Comentario;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.DatosServicio;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.DetallesServicio;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.MensajeMonitoreo;
import com.bersamed.ServidorWeb.Estructuras.Json.OrdenesServicio.Trabajador.Servicio;
import com.bersamed.ServidorWeb.Repositorios.BitacoraServicioRepositorio;

import jakarta.transaction.Transactional;
@Service
public class ordenesservicioServicio {
    private final BitacoraServicioRepositorio servicioRepositorio;

    public ordenesservicioServicio(BitacoraServicioRepositorio servicioRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
    }

    @Transactional
    public List<Servicio> getAllServicios() {
        List<BitacoraServicioEntidad> servicios = servicioRepositorio.findAll();
        return mapearEntidadADto(servicios);
    }

    @Transactional
    public List<Servicio> getServiciosPorTrabajador(Long idTrabajador) {
        List<BitacoraServicioEntidad> servicios = servicioRepositorio.findByTrabajador(idTrabajador);
        return mapearEntidadADto(servicios);
    }

    /**
     * 👇 Aquí concentras todo el mapeo de Entidad → DTO
     */
    private List<Servicio> mapearEntidadADto(List<BitacoraServicioEntidad> entidades) {
        List<Servicio> responses = new ArrayList<>();

        for (BitacoraServicioEntidad s : entidades) {
            Servicio response = new Servicio();
            response.setIdServicio(s.getCodigo());

            // DatosServicio
            DatosServicio datos = new DatosServicio();
            datos.setRazonSocial(s.getRazonSocial());
            datos.setHora(s.getHora());
            datos.setDireccion("Ubicación trabajador desde Android");
            datos.setDireccionServicio(s.getDireccionCompleta());

            Comentario comentario = new Comentario();
            comentario.setAutorDelComentario(s.getAutorDelComentario());
            comentario.setFalla(s.getFalla());
            datos.setComentario(comentario);
            response.setDatosServicio(datos);

            // DetallesServicio
            if (s.getBitacoraServicioDetalleEntidad() != null) {
                DetallesServicio detalles = new DetallesServicio();
                detalles.setNumeroDeOrden(s.getBitacoraServicioDetalleEntidad().getBcodigo());
                detalles.setNumeroDeServicio(s.getBitacoraServicioDetalleEntidad().getPartida());
                detalles.setIdDelTrabajadorResponsable(s.getBitacoraServicioDetalleEntidad().getUsuarioresponsable());

                if (s.getBitacoraServicioDetalleEntidad().getTrabajadorResponsable().getAbreviatura().trim().isEmpty()) {
                    detalles.setNombreDelTrabajadorResponsable(
                        s.getBitacoraServicioDetalleEntidad().getTrabajadorResponsable().getNombre()
                    );
                } else {
                    detalles.setNombreDelTrabajadorResponsable(
                        s.getBitacoraServicioDetalleEntidad().getTrabajadorResponsable().getAbreviatura() + " " +
                        s.getBitacoraServicioDetalleEntidad().getTrabajadorResponsable().getNombre()
                    );
                }

                datos.setEstadoServicio(
                    EstadoDelServicio(
                        s.getBitacoraServicioDetalleEntidad().isConautomovil(),
                        s.getBitacoraServicioDetalleEntidad().isConentransito(),
                        s.getBitacoraServicioDetalleEntidad().isConatendiendo(),
                        s.getBitacoraServicioDetalleEntidad().isConresuelto(),
                        s.getBitacoraServicioDetalleEntidad().isConnoresuelto(),
                        s.getBitacoraServicioDetalleEntidad().isConretorno(),
                        s.getBitacoraServicioDetalleEntidad().isConparo(),
                        s.getBitacoraServicioDetalleEntidad().isContermino(),
                        s.getBitacoraServicioDetalleEntidad().isConvisitaprogramada(),
                        s.getBitacoraServicioDetalleEntidad().isConinicio()
                    )
                );

                response.setDetallesServicio(detalles);
            }

            // MensajesMonitoreo
            List<MensajeMonitoreo> mensajesResponse = new ArrayList<>();
            if (s.getBitacoraServicioMonitoreoEntidad() != null) {
                for (var m : s.getBitacoraServicioMonitoreoEntidad()) {
                    MensajeMonitoreo msg = new MensajeMonitoreo();
                    msg.setCodigoMensaje(s.getBitacoraServicioDetalleEntidad().getBcodigo());
                    msg.setFechaRegistroDelMensaje(m.getFecharegistro());
                    msg.setHoraRegistroDelMensaje(m.getHoraregistro());
                    msg.setUsuarioQueRegistroElMensaje(m.getUsuarioregistro());
                    msg.setMensajeDelProblema(m.getDescripcion());
                    mensajesResponse.add(msg);
                }
            }
            response.setMensajeMonitoreo(mensajesResponse);

            responses.add(response);
        }
        return responses;
    }

    private String EstadoDelServicio(boolean Automovil, boolean EnTransito,
                                     boolean Atendido, boolean Resuelto, boolean NoResuelto,
                                     boolean Retorno, boolean Paro, boolean Termino,
                                     boolean VisitaProgramada, boolean Inicio) {
        if (Automovil) return "En Automovil";
        if (EnTransito) return "En Transito";
        if (Atendido) return "Atendido";
        if (Resuelto) return "Resuelto";
        if (NoResuelto) return "No Resuelto";
        if (Retorno) return "En Retorno";
        if (Paro) return "En Paro";
        if (Termino) return "En Termino";
        if (VisitaProgramada) return "Con Visita Programada";
        if (Inicio) return "Inicio";
        return null;
    }
}
