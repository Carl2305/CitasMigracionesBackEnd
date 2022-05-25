package com.cita.migraciones.servicelayer;

import java.util.List;
import java.util.Optional;

import com.cita.migraciones.entitylayer.Cupo;

public interface CupoService {
	
	public List<Cupo> listCupo();
	public Optional<Cupo> getCupo(int idCupo);
	public Cupo saveAndUpdateCupo(Cupo cupo);
	public void deteleCupo(int idCupo);
	public abstract List<Cupo> ListaCupoPorSede(int idSede);

}
