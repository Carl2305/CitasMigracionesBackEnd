package com.cita.migraciones.servicelayer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cita.migraciones.entitylayer.Sede;
import com.cita.migraciones.repository.SedeRepository;

@Service
public class SedeServiceImpl implements SedeService {

	@Autowired
	private SedeRepository repository;
	
	@Override
	public List<Sede> listSede() {
		return repository.findAll();
	}

}
