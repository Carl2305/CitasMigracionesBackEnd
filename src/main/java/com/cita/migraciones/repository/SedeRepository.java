package com.cita.migraciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cita.migraciones.entitylayer.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer> {

}
