package com.cita.migraciones.servicelayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cita.migraciones.entitylayer.Cupo;
import com.cita.migraciones.repository.CupoRepository;

@Service
public class CupoServiceImpl implements CupoService {
	
	@Autowired
	private CupoRepository repository;

	@Override
	public List<Cupo> listCupo() {
		return repository.findAll();
	}

	@Override
	public Optional<Cupo> getCupo(int idCupo) {
		return repository.findById(idCupo);
	}

	@Override
	public Cupo saveAndUpdateCupo(Cupo cupo) {
		return repository.save(cupo);
	}

	@Override
	public void deteleCupo(int idCupo) {
		// TODO Auto-generated method stub
		repository.deleteById(idCupo);
	}

}
