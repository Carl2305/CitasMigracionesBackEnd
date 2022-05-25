package com.cita.migraciones.controller;

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

import com.cita.migraciones.entitylayer.Cupo;
import com.cita.migraciones.servicelayer.CupoService;

@RestController
@RequestMapping("/api/cupo")
@CrossOrigin
public class CupoController {
	@Autowired
	private CupoService cupoService;
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Cupo>> listCupo(){
		return new ResponseEntity<>(cupoService.listCupo(), HttpStatus.OK);
	}
	
	@GetMapping("/{idCupo}")
	@ResponseBody
	public ResponseEntity<Optional<Cupo>> getRecibo(@PathVariable(name = "idCupo") int idCupo){
		return new ResponseEntity<>(cupoService.getCupo(idCupo), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Cupo> saveRecibo(@RequestBody Cupo cupo){
		cupo.setIdCupo(0);
		cupo.setEstado(false);
		return new ResponseEntity<>(cupoService.saveAndUpdateCupo(cupo), HttpStatus.OK);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Cupo> updateRecibo(@RequestBody Cupo cupo){
		cupo.setEstado(true);
		return new ResponseEntity<>(cupoService.saveAndUpdateCupo(cupo), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idCupo}")
	@ResponseBody
	public ResponseEntity<String> deleteRecibo(@PathVariable(name = "idCupo") int idCupo){
		cupoService.deteleCupo(idCupo);
		return new ResponseEntity<>("Cita Eliminada Correctamente", HttpStatus.OK);
	}
}
