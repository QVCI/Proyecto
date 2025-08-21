package com.bersamed.ServidorWeb.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador.BitacoraServicio;

@Repository
public interface BitacoraServicioRepositorio extends JpaRepository<BitacoraServicio, String> {
    //Obtener todas las bitacoras de servicio
}