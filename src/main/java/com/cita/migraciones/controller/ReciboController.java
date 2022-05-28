package com.cita.migraciones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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
	public ResponseEntity<HashMap<String, Object>> saveRecibo(@RequestBody Recibo recibo){
		
		HashMap<String, Object> salida = new HashMap<String, Object>();
		
		try
		{
			List<Recibo> lstRecibo = reciboService.listaReciboPorID(recibo.getCodigoVoucher());
			
			if (CollectionUtils.isEmpty(lstRecibo)) 
					{
						recibo.setId_recibo(0);
						Recibo objSalida = reciboService.saveUpdateRecibo(recibo);
						if (objSalida == null) {
							salida.put("mensaje", "Error en el registro");
							salida.put("status", "error");
						} else {
							salida.put("mensaje", "Registro exitoso");
							salida.put("status", HttpStatus.OK);
						}
					} 
					else 
					{
						salida.put("mensaje", "El codigo de voucher ya fue usado : " + recibo.getCodigoVoucher());
						salida.put("status", "error");
					}

		}
		catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro " + e.getMessage());
			salida.put("status", "error");
		}
		
		return ResponseEntity.ok(salida);
		
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Recibo> updateRecibo(@RequestBody Recibo recibo){
		return new ResponseEntity<>(reciboService.saveUpdateRecibo(recibo), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idRecibo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> deleteRecibo(@PathVariable(name = "idRecibo") int idRecibo){
		reciboService.deleteRecibo(idRecibo);
		HashMap<String, Object> salida = new HashMap<String, Object>();
		salida.put("mensaje", "Recibo Eliminado Correctamente");
		salida.put("status", HttpStatus.OK);
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
}
