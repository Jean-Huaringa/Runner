package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Persona;

public interface PersonaService {

	// LISTADO GENERAL DE PERSONAS
	public ResponseEntity<SuccessResponse<List<Persona>>> findAllPersonas();
	
	// BUSQUEDA DE PERSONA POR ID
	public ResponseEntity<SuccessResponse<Persona>> findByIdPersona(Integer id);
	
	// CREAR PERSONA
	public ResponseEntity<SuccessResponse<Persona>> savePersona(Persona p);
	
	// ACTUALIZAR PERSONA
	public ResponseEntity<SuccessResponse<Persona>> updatePersona(Persona p, Integer id);
	
	// ELIMINAR PERSONA
	public ResponseEntity<SuccessResponse<String>> deleteByIdPersona(Integer id);
}
