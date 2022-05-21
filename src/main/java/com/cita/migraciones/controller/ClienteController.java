package com.cita.migraciones.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<Optional<Cliente>> listCliente(){
		return new ResponseEntity<>(clienteService.getCliente("72486510"), HttpStatus.OK);
	}
	
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
	public ResponseEntity<Cliente> SaveCliente(@RequestBody Cliente cliente){
		DniResponseDTO data= callAPI(cliente.getDNI());
		Cliente objResponse= new Cliente();
		if(data.isSuccess()) {
			String pass=cliente.getPassword();
			cliente.setNombre(data.getData().getNombres());
			cliente.setApePaterno(data.getData().getApellido_paterno());
			cliente.setApeMaterno(data.getData().getApellido_materno());
			cliente.setPassword(new SecurityConfig().passwordEncoder().encode(pass));
			objResponse=clienteService.saveUpdateCliente(cliente);
		}else {
			objResponse= new Cliente();
		}
		return new ResponseEntity<>(objResponse, HttpStatus.OK);
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
		DniResponseDTO data= callAPI(cliente.getDNI());
		Cliente objResponse= new Cliente();
		if(data.isSuccess()) {
			cliente.setNombre(data.getData().getNombres());
			cliente.setApePaterno(data.getData().getApellido_paterno());
			cliente.setApeMaterno(data.getData().getApellido_materno());
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
