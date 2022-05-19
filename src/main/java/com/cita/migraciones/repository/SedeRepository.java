package com.cita.migraciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cita.migraciones.entitylayer.Sede;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {

}
