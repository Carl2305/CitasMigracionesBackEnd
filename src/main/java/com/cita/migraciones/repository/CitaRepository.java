package com.cita.migraciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cita.migraciones.entitylayer.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
	
	@Query("select e from Cita e where e.cliente.DNI =?1")
	public List<Cita> listaCitaporDni(String DNI);

}
