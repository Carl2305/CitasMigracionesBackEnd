package com.cita.migraciones.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DniResponseDTO {
	private boolean success;
	private String message;
	private DniDataResponseDTO data;
	private String source;
	
	
	
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class DniDataResponseDTO{
		private String numero;
		private String nombre_completo;
		private String nombres;
		private String apellido_paterno;
		private String apellido_materno;
		private String codigo_verificacion;
		private String fecha_nacimiento;
		private String sexo;
		private String estado_civil;
		private String departamento;
		private String provincia;
		private String distrito;
		private String direccion;
		private String direccion_completa;
		private String ubigeo_reniec;
		private String ubigeo_sunat;
		private String[] ubigeo;
	}
}

