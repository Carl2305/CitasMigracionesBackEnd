package com.cita.migraciones.entitylayer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sede")
public class Sede {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSede;
	private String nombre;
}
