package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Persona;

public interface PersonaService {

	// LISTADO GENERAL DE PERSONAS
	public ResponseEntity<Map<String, Object>> findAllPersonas();
	
	// BUSQUEDA DE PERSONA POR ID
	public ResponseEntity<Map<String, Object>> findByIdPersona(Integer id);
	
	// CREAR PERSONA
	public ResponseEntity<Map<String, Object>> savePersona(Persona p);
	
	// ACTUALIZAR PERSONA
	public ResponseEntity<Map<String, Object>> updatePersona(Persona p, Integer id);
	
	// ELIMINAR PERSONA
	public ResponseEntity<Map<String, Object>> deleteByIdPersona(Integer id);
}
