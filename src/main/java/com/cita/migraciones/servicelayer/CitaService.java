package com.cita.migraciones.servicelayer;

import java.util.List;
import java.util.Optional;
import com.cita.migraciones.entitylayer.Cita;

public interface CitaService {
	public List<Cita> listCita();
	public Optional<Cita> getCita(int idCita);
	public Cita saveAndUpdateCita(Cita cita);
	public void deleteCita(int idCita);
}
