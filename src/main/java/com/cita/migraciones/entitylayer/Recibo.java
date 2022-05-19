package com.cita.migraciones.entitylayer;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "recibo")
public class Recibo {

	@Id
	private int idRecibo;
	private String codigoVerificacion;
	private Date fechaPago;
	
	public Recibo(int idRecibo) {
		this.idRecibo = idRecibo;
	}
}
