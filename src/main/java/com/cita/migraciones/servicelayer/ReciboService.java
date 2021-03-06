package com.cita.migraciones.servicelayer;

import java.util.List;
import java.util.Optional;

import com.cita.migraciones.entitylayer.Recibo;

public interface ReciboService {
	public List<Recibo> listRecibo();
	public Optional<Recibo> getRecibo(int idRecibo);
	public Recibo saveUpdateRecibo(Recibo recibo);
	public void deleteRecibo(int idRecibo);
	
	public abstract List <Recibo> listaReciboPorID(String codigoVoucher);
}
