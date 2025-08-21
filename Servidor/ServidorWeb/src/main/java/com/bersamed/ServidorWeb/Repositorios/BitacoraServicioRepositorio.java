package com.bersamed.ServidorWeb.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador.BitacoraServicioEntidad;

@Repository
public interface BitacoraServicioRepositorio extends JpaRepository<BitacoraServicioEntidad, String>
{
    
}

