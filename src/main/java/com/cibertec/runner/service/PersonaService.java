package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Persona;

public interface PersonaService {

	// LISTADO GENERAL DE PERSONAS
	public ResponseEntity<Map<String, Object>> listPersonas();
	
	// BUSQUEDA DE PERSONA POR ID
	public ResponseEntity<Map<String, Object>> listPersonaById(Integer id);
	
	// CREAR PERSONA
	public ResponseEntity<Map<String, Object>> createPersona(Persona p);
	
	// ACTUALIZAR PERSONA
	public ResponseEntity<Map<String, Object>> updatePersona(Persona p, Integer id);
	
	// ELIMINAR PERSONA
	public ResponseEntity<Map<String, Object>> deletePersona(Integer id);
}
