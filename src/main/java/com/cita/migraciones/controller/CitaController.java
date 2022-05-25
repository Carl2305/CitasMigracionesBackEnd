package com.cita.migraciones.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
	public ResponseEntity<List<Cita>> listCita(){
		return new ResponseEntity<>(citaService.listCita(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{idCita}")
	@ResponseBody
	public ResponseEntity<Optional<Cita>> getRecibo(@PathVariable(name = "idCita") int idCita){
		return new ResponseEntity<>(citaService.getCita(idCita), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Cita> saveRecibo(@RequestBody Cita cita){
		cita.setIdCita(0);
		cita.setFechaRegistro(new Date());
		return new ResponseEntity<>(citaService.saveAndUpdateCita(cita), HttpStatus.OK);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Cita> updateRecibo(@RequestBody Cita cita){
		cita.setFechaRegistro(new Date());
		return new ResponseEntity<>(citaService.saveAndUpdateCita(cita), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idCita}")
	@ResponseBody
	public ResponseEntity<String> deleteRecibo(@PathVariable(name = "idCita") int idCita){
		citaService.deleteCita(idCita);
		return new ResponseEntity<>("Cita Eliminada Correctamente", HttpStatus.OK);
	}
}
