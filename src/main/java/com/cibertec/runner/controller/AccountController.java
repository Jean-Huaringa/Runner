package com.cibertec.runner.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.request.LoginDTO;
import com.cibertec.runner.dto.request.RegisterUserDTO;
import com.cibertec.runner.dto.request.UpdatePasswordDTO;
import com.cibertec.runner.dto.request.UpdateUserDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Usuario;
import com.cibertec.runner.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService userService;

	@PostMapping("/register")
	public ResponseEntity<SuccessResponse<String>> registerUser(@RequestBody RegisterUserDTO request) {
		
		userService.registerUser(request);
		
		SuccessResponse<String> success = new SuccessResponse<String>();
		success.setTimestamp(LocalDateTime.now());
		success.setStatus(HttpStatus.CREATED.value());
		success.setSuccess(HttpStatus.CREATED.getReasonPhrase());
		success.setResponse("Usuario se creo correctamente");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

	@PutMapping("/update-user")
	public ResponseEntity<SuccessResponse<String>> updateUser(@RequestBody UpdateUserDTO request) {
		userService.updateUser(request);
		
		SuccessResponse<String> success = new SuccessResponse<String>();
		success.setTimestamp(LocalDateTime.now());
		success.setStatus(HttpStatus.OK.value());
		success.setSuccess(HttpStatus.OK.getReasonPhrase());
		success.setResponse("");
		
		return ResponseEntity.ok(success);
	}

	@PostMapping("/signin")
	public ResponseEntity<SuccessResponse<String>> signin(@RequestBody LoginDTO request) {
		String mensaje = userService.signin(request);

		SuccessResponse<String> success = new SuccessResponse<String>();
		success.setTimestamp(LocalDateTime.now());
		success.setStatus(HttpStatus.OK.value());
		success.setSuccess(HttpStatus.OK.getReasonPhrase());
		success.setResponse(mensaje);
		return ResponseEntity.ok(success);
	}

	@PutMapping("/update-contrasenia")
	public ResponseEntity<SuccessResponse<String>> updateClave(@RequestBody UpdatePasswordDTO request) {
		userService.updateClave(request);
		SuccessResponse<String> success = new SuccessResponse<String>();
		success.setTimestamp(LocalDateTime.now());
		success.setStatus(HttpStatus.OK.value());
		success.setSuccess(HttpStatus.OK.getReasonPhrase());
		success.setResponse("");
		return ResponseEntity.ok(success);
	}

	@GetMapping("/user")
	public ResponseEntity<SuccessResponse<Usuario>> getUsuarioLogueado() {
		Usuario token = userService.getUsuarioLogueado();
		SuccessResponse<Usuario> success = new SuccessResponse<Usuario>();
		success.setTimestamp(LocalDateTime.now());
		success.setStatus(HttpStatus.OK.value());
		success.setSuccess(HttpStatus.OK.getReasonPhrase());
		success.setResponse(token);
		return ResponseEntity.ok(success);
	}
}
