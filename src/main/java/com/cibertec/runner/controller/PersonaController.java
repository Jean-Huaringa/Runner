package com.cibertec.runner.controller;

import java.util.Map;

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

import com.cibertec.runner.model.Persona;
import com.cibertec.runner.service.implement.PersonaServiceImp;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
	
	@Autowired
	private PersonaServiceImp ps;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> findAllPersonas() {
		return ps.findAllPersonas();
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Map<String, Object>> findByIdPersona(@PathVariable Integer id) {
		return ps.findByIdPersona(id);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Map<String, Object>> savePersona(@RequestBody Persona p) {
		return ps.savePersona(p);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, Object>> updatePersona(@RequestBody Persona p, @PathVariable Integer id) {
		return ps.updatePersona(p, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> deleteByIdPersona(@PathVariable Integer id) {
		return ps.deleteByIdPersona(id);
	}

}
