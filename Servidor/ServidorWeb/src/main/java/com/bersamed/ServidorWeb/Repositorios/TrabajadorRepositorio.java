package com.bersamed.ServidorWeb.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bersamed.ServidorWeb.Estructuras.Entidades.TrabajadorEntidad;

//Querry para buscar por login
@Repository
public interface TrabajadorRepositorio extends JpaRepository<TrabajadorEntidad, Long> {

    Optional<TrabajadorEntidad> findByLogin(String login);
}

