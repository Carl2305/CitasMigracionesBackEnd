package com.cita.migraciones.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cita.migraciones.entitylayer.Sede;
import com.cita.migraciones.servicelayer.SedeService;

@RestController
@RequestMapping("/api/sede")
@CrossOrigin
public class SedeController {
	
	@Autowired
	private SedeService sedeService;
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Sede>> listReclamo(){
		return ResponseEntity.ok(sedeService.listSede());
	} 
}
