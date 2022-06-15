package com.cita.migraciones.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cita.migraciones.servicelayer.SedeService;

@RestController
@RequestMapping("/api/sede")
@CrossOrigin
public class SedeController {
	
	@Autowired
	private SedeService sedeService;
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> listReclamo(){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", sedeService.listSede());
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new ArrayList<>());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	} 
}
