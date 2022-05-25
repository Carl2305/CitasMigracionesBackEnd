package com.cita.migraciones.servicelayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cita.migraciones.entitylayer.Recibo;
import com.cita.migraciones.repository.ReciboRepository;

@Service
public class ReciboServiceImpl implements ReciboService {
	
	@Autowired
	private ReciboRepository repository;

	@Override
	public List<Recibo> listRecibo() {
		return repository.findAll();
	}

	@Override
	public Optional<Recibo> getRecibo(int idRecibo) {
		return repository.findById(idRecibo);
	}

	@Override
	public Recibo saveUpdateRecibo(Recibo recibo) {
		return repository.save(recibo);
	}

	@Override
	public void deleteRecibo(int idRecibo) {
		repository.deleteById(idRecibo);
	}
	
	@Override
	public List<Recibo> listaReciboPorID(String codigoVoucher) {
		// TODO Auto-generated method stub
		return repository.listaReciboPorID(codigoVoucher);
	}

}
