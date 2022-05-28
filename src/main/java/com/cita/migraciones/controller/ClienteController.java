package com.cita.migraciones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cita.migraciones.entitylayer.Cliente;
import com.cita.migraciones.servicelayer.ClienteService;
import com.cita.migraciones.util.DniResponseDTO;
import com.cita.migraciones.util.ForgotPasswordRequestDTO;
import com.cita.migraciones.util.SecurityConfig;
import com.cita.migraciones.util.SignInRequestDTO;
import com.cita.migraciones.util.SignInResponseDTO;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	

	@PostMapping("/signIn")
	@ResponseBody
	public ResponseEntity<SignInResponseDTO> LogIn(@RequestBody SignInRequestDTO signIn){
		BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
		Optional<Cliente> dataCli= clienteService.getCliente(signIn.getDni());
		
		boolean resp= passEncode.matches(signIn.getPassword(), dataCli.get().getPassword());
		SignInResponseDTO response= new SignInResponseDTO();
		if(resp) {
			response.setSuccess(true);
		}else {
			response.setSuccess(false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/signUp")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> SaveCliente(@RequestBody Cliente cliente){
		
		HashMap<String, Object> salida = new HashMap<String, Object>();
	
		try
		{
			List<Cliente> lstCliente = clienteService.listaClienteporDni(cliente.getDNI());
			List<Cliente> lstCliente2 = clienteService.listaClienteporCorreo(cliente.getCorreo());
			
			
			if(CollectionUtils.isEmpty(lstCliente2))
			{
				if (CollectionUtils.isEmpty(lstCliente))
				{
					DniResponseDTO data= callAPI(cliente.getDNI());
					Cliente objResponse= new Cliente();
					if(data.isSuccess()) {
						String pass=cliente.getPassword();
						cliente.setNombre(data.getData().getNombres());
						cliente.setApePaterno(data.getData().getApellido_paterno());
						cliente.setApeMaterno(data.getData().getApellido_materno());
						cliente.setPassword(new SecurityConfig().passwordEncoder().encode(pass));
						objResponse=clienteService.saveUpdateCliente(cliente);
						salida.put("mensaje", "Se registra correctamente al cliente");
						salida.put("status", HttpStatus.OK);
					}else {
						objResponse= new Cliente();
						salida.put("mensaje", "DNI no existe");
						salida.put("status", "error");
					}
				}
				else
				{
					salida.put("mensaje", "El cliente ya esta registrado : " + cliente.getDNI());
					salida.put("status", "error");
				}
			}
			else
			{
				salida.put("mensaje", "El correo ya esta siendo utilizado : " + cliente.getCorreo());
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
	
	@PostMapping("/ftpass")
	@ResponseBody
	public ResponseEntity<SignInResponseDTO> forgotpassCliente( @RequestBody ForgotPasswordRequestDTO fgtpass){
		Optional<Cliente> dataClient= clienteService.getCliente(fgtpass.getDni());
		SignInResponseDTO response= new SignInResponseDTO();
		if(dataClient.get().getDNI().equals(fgtpass.getDni())) {
			BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
			if(passEncode.matches(fgtpass.getPassword(), dataClient.get().getPassword())) {
				passEncode= new BCryptPasswordEncoder();
				dataClient.get().setPassword(passEncode.encode(fgtpass.getPasswordNew()));
				clienteService.saveUpdateCliente(dataClient.get());
				response.setSuccess(true);
			}else {
				response.setSuccess(false);
			}
		}else {
			response.setSuccess(false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/signUp")
	@ResponseBody
	public ResponseEntity<Cliente> UpdateCliente(@RequestBody Cliente cliente){
		Optional<Cliente> dataClient= clienteService.getCliente(cliente.getDNI());
		Cliente objResponse= new Cliente();
		if(dataClient!=null) {
			cliente.setNombre(dataClient.get().getNombre());
			cliente.setApePaterno(dataClient.get().getApePaterno());
			cliente.setApeMaterno(dataClient.get().getApeMaterno());
			cliente.setPassword(dataClient.get().getPassword());
			objResponse=clienteService.saveUpdateCliente(cliente);
		}else {
			objResponse= new Cliente();
		}
		return new ResponseEntity<>(objResponse, HttpStatus.OK);
	}
	
	private DniResponseDTO callAPI(String dni) {
		String URI="https://apiperu.dev/api/dni/"+dni+"?api_token=6703575c39271aa186609a38725bf4a758aef76c0d27356e34cfbb88dfb7dd35";
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity<DniResponseDTO> result=restTemplate.getForEntity(URI, DniResponseDTO.class);
		return result.getBody();
	}
}
