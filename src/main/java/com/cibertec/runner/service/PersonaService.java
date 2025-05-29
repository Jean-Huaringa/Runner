package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Persona;

public interface PersonaService {

	public ResponseEntity<SuccessResponse<List<Persona>>> findAllPersonas();
	
	public ResponseEntity<SuccessResponse<Persona>> findByIdPersona(Integer id);
	
	public ResponseEntity<SuccessResponse<Persona>> savePersona(Persona p);
	
	public ResponseEntity<SuccessResponse<Persona>> updatePersona(Persona p, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdPersona(Integer id);
}
