package com.bersamed.ServidorWeb.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bersamed.ServidorWeb.Estructuras.Entidades.ClienteEntidad;

//Querry para buscar por razón social
@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteEntidad, Long> {

    Optional<ClienteEntidad> findByRazonSocial(String razonsocial);
}
