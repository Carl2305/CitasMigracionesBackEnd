package com.cita.migraciones.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cita.migraciones.entitylayer.Cita;
import com.cita.migraciones.servicelayer.CitaService;

@RestController
@RequestMapping("/api/cita")
@CrossOrigin
public class CitaController {
	@Autowired
	private CitaService citaService;
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> listCita(){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", citaService.listCita());
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new ArrayList<>());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	
	@GetMapping("/{idCita}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> getCita(@PathVariable(name = "idCita") int idCita){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", citaService.getCita(idCita));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@GetMapping("/DNI/{DNI}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> listaCitaporDni(@PathVariable(name = "DNI") String DNI){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", citaService.listaCitaporDni(DNI));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> saveCita(@RequestBody Cita cita){
		
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			cita.setIdCita(0);
			cita.setFechaRegistro(new Date());
			salida.put("data", citaService.saveAndUpdateCita(cita));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> updateCita(@RequestBody Cita cita){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			cita.setFechaRegistro(new Date());
			salida.put("data", citaService.saveAndUpdateCita(cita));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idCita}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> deleteCita(@PathVariable(name = "idCita") int idCita){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			citaService.deleteCita(idCita);
			salida.put("mensaje", "Cita Eliminada Correctamente");
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("mensaje", "Error en el Eliminar" + e.getMessage());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
}
