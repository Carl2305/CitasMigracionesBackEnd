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
import com.cita.migraciones.entitylayer.Recibo;
import com.cita.migraciones.servicelayer.ReciboService;

@RestController
@RequestMapping("/api/recibo")
@CrossOrigin
public class ReciboController {
	@Autowired
	private ReciboService reciboService;
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Recibo>> listRecibo(){
		return new ResponseEntity<>(reciboService.listRecibo(), HttpStatus.OK);
	}
	
	@GetMapping("/{idRecibo}")
	@ResponseBody
	public ResponseEntity<Optional<Recibo>> getRecibo(@PathVariable(name = "idRecibo") int idRecibo){
		return new ResponseEntity<>(reciboService.getRecibo(idRecibo), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Recibo> saveRecibo(@RequestBody Recibo recibo){
		recibo.setIdRecibo(0);
		return new ResponseEntity<>(reciboService.saveUpdateRecibo(recibo), HttpStatus.OK);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Recibo> updateRecibo(@RequestBody Recibo recibo){
		return new ResponseEntity<>(reciboService.saveUpdateRecibo(recibo), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idRecibo}")
	@ResponseBody
	public ResponseEntity<String> deleteRecibo(@PathVariable(name = "idRecibo") int idRecibo){
		reciboService.deleteRecibo(idRecibo);
		return new ResponseEntity<>("Recibo Eliminado Correctamente", HttpStatus.OK);
	}
}
