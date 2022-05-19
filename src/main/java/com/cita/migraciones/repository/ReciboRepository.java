package com.cita.migraciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cita.migraciones.entitylayer.Recibo;

@Repository
public interface ReciboRepository extends JpaRepository<Recibo, Integer> {

}
