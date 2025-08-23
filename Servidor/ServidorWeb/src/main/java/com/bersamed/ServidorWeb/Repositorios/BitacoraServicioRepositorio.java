package com.bersamed.ServidorWeb.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador.BitacoraServicioEntidad;

@Repository
public interface BitacoraServicioRepositorio extends JpaRepository<BitacoraServicioEntidad, String>
{
    @Query("SELECT b FROM BitacoraServicioEntidad b " +
           "JOIN b.bitacoraServicioDetalleEntidad d " +
           "WHERE d.usuarioresponsable = :idTrabajador")
    List<BitacoraServicioEntidad> findByTrabajador(@Param("idTrabajador") Long idTrabajador);
}

