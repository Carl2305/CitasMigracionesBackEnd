package com.cita.migraciones.servicelayer;

import java.util.List;
import java.util.Optional;

import com.cita.migraciones.entitylayer.Cliente;

public interface ClienteService {
	
	public Optional<Cliente> LogIn(String email, String password);
	public Cliente saveUpdateCliente(Cliente cliente);
	public List<Cliente> listCliente();
	public Optional<Cliente> getCliente(String dni);
	public abstract List <Cliente> listaClienteporDni(String DNI);
	public abstract List <Cliente> listaClienteporCorreo(String correo);
	
}
