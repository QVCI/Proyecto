package com.bersamed.ServidorWeb.infraestructura.entidad.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bersamed.ServidorWeb.infraestructura.entidad.UsuarioEntidad;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioEntidad, Long> {

    Optional<UsuarioEntidad> findByLogin(String login);
}

