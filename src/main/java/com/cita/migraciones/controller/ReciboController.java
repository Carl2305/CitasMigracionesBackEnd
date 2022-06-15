package com.cita.migraciones.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	public ResponseEntity<HashMap<String, Object>> listRecibo(){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", reciboService.listRecibo());
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new ArrayList<>());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@GetMapping("/{idRecibo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> getRecibo(@PathVariable(name = "idRecibo") int idRecibo){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", reciboService.getRecibo(idRecibo));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
	
	@GetMapping("/cv/{codigoVoucher}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> getReciboCodigoVoucher(@PathVariable(name = "codigoVoucher") String codigoVoucher){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			salida.put("data", reciboService.listaReciboPorID(codigoVoucher).get(0));
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("data", new Object());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
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
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(salida, HttpStatus.OK);
		
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Recibo> updateRecibo(@RequestBody Recibo recibo){
		return new ResponseEntity<>(reciboService.saveUpdateRecibo(recibo), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idRecibo}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> deleteRecibo(@PathVariable(name = "idRecibo") int idRecibo){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			reciboService.deleteRecibo(idRecibo);
			salida.put("mensaje", "Recibo Eliminado Correctamente");
			salida.put("status", HttpStatus.OK);
		} catch (Exception e) {
			salida.put("mensaje", "Error en el Eliminar" + e.getMessage());
			salida.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(salida, HttpStatus.OK);
	}
}
