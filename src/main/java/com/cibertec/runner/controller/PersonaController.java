package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Persona;
import com.cibertec.runner.service.implement.PersonaServiceImp;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
	
	@Autowired
	private PersonaServiceImp ps;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Persona>>> findAllPersonas() {
		return ps.findAllPersonas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Persona>> findByIdPersona(@PathVariable Integer id) {
		return ps.findByIdPersona(id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Persona>> savePersona(@RequestBody Persona p) {
		return ps.savePersona(p);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Persona>> updatePersona(@RequestBody Persona p, @PathVariable Integer id) {
		return ps.updatePersona(p, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdPersona(@PathVariable Integer id) {
		return ps.deleteByIdPersona(id);
	}

}
