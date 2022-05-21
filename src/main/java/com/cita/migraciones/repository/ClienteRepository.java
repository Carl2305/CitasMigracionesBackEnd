package com.cita.migraciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cita.migraciones.entitylayer.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
