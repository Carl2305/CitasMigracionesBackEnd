package com.cita.migraciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cita.migraciones.entitylayer.Cupo;

@Repository
public interface CupoRepository extends JpaRepository<Cupo, Integer> {

}
