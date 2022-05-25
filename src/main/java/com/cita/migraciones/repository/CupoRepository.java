package com.cita.migraciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cita.migraciones.entitylayer.Cupo;

@Repository
public interface CupoRepository extends JpaRepository<Cupo, Integer> {
	
	@Query("select e from Cupo e where e.sede.idSede =?1")
	public List<Cupo> ListaCupoPorSede(int idSede);

}
