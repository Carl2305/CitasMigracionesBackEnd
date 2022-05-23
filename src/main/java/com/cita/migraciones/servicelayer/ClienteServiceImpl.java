package com.cita.migraciones.servicelayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cita.migraciones.entitylayer.Cliente;
import com.cita.migraciones.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public Optional<Cliente> LogIn(String email, String password) {
		return null;
	}

	@Override
	public Cliente saveUpdateCliente(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public List<Cliente> listCliente() {
		return repository.findAll();
	}

	@Override
	public Optional<Cliente> getCliente(String dni) {
		return repository.findById(dni);
	}
	
	@Override
	public List<Cliente> listaClienteporDni(String DNI) {
	return repository.listaporDNI(DNI);
	}
}
