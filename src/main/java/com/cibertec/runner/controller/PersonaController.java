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
import com.cibertec.runner.serviceImp.PersonaServiceImp;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
	
	@Autowired
	private PersonaServiceImp ps;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> list() {
		return ps.listPersonas();
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Map<String, Object>> listById(@PathVariable Integer id) {
		return ps.listPersonaById(id);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Map<String, Object>> create(@RequestBody Persona p) {
		return ps.createPersona(p);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Persona p, @PathVariable Integer id) {
		return ps.updatePersona(p, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
		return ps.deletePersona(id);
	}

}
