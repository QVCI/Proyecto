package com.bersamed.ServidorWeb.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bersamed.ServidorWeb.Estructuras.Entidades.ClienteEntidad;
import com.bersamed.ServidorWeb.Estructuras.Entidades.SubEntidades.ClienteInfoDatos;


public interface ClienteRepositorio extends JpaRepository<ClienteEntidad, Long> {

@Query(value = """
    SELECT c.idcliente, c.razonsocial, c.rfc
    FROM correoselectronicos ce
    JOIN clientecorreo cc ON ce.id_correo = cc.correo
    JOIN cliente c ON cc.idcliente = c.idcliente
    WHERE ce.correoelectronico = :correo
    LIMIT 1
    """, nativeQuery = true)
    ClienteInfoDatos findClienteInfoByCorreo(@Param("correo") String correo);



}
