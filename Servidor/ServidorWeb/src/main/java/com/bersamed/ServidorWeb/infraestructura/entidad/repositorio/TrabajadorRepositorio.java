package com.bersamed.ServidorWeb.infraestructura.entidad.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bersamed.ServidorWeb.infraestructura.entidad.TrabajadorEntidad;

@Repository
public interface TrabajadorRepositorio extends JpaRepository<TrabajadorEntidad, Long> {

    Optional<TrabajadorEntidad> findByLogin(String login);
}

