package com.cita.migraciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cita.migraciones.entitylayer.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
	
	@Query("select e from Cliente e where e.DNI =?1")
	public List<Cliente> listaporDNI(String DNI);
	
	@Query("select e from Cliente e where e.correo =?1")
	public List<Cliente> listaporCorreo(String correo);
	
	
}
