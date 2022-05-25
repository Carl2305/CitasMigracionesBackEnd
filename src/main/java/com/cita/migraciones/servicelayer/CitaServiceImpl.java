package com.cita.migraciones.servicelayer;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cita.migraciones.entitylayer.Cita;
import com.cita.migraciones.repository.CitaRepository;

@Service
public class CitaServiceImpl implements CitaService {

	@Autowired
	private CitaRepository repository;
	
	@Override
	public List<Cita> listCita() {
		return repository.findAll();
	}

	@Override
	public Optional<Cita> getCita(int idCita) {
		return repository.findById(idCita);
	}

	@Override
	public Cita saveAndUpdateCita(Cita cita) {
		return repository.save(cita);
	}

	@Override
	public void deleteCita(int idCita) {
		repository.deleteById(idCita);
	}

	@Override
	public List<Cita> listaCitaporDni(String DNI) {
		// TODO Auto-generated method stub
		return repository.listaCitaporDni(DNI);
	}
}
