package com.cita.migraciones.controller;

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
	public ResponseEntity<HashMap<String, Object>> listCupo(){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", cupoService.listCupo());
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@GetMapping("/sede/{idSede}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> ListaCupoPorSede(@PathVariable(name = "idSede") int idSede){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", cupoService.ListaCupoPorSede(idSede));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> saveCupo(@RequestBody Cupo cupo){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			cupo.setIdCupo(0);
			cupo.setEstado(false);
			salida.put("data", cupoService.saveAndUpdateCupo(cupo));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> updateCupo(@RequestBody Cupo cupo){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			cupo.setEstado(true);
			salida.put("data", cupoService.saveAndUpdateCupo(cupo));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idCupo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object> > deleteCupo(@PathVariable(name = "idCupo") int idCupo){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			cupoService.deteleCupo(idCupo);
			salida.put("mensaje", "Cupo Eliminado Correctamente");
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("mensaje", "Error en el Eliminar" + e.getMessage());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
}
