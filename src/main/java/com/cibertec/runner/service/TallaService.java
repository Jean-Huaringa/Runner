package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Talla;

public interface TallaService {
	
	public ResponseEntity<Map<String, Object>> findAllTalla();
	
	public ResponseEntity<Map<String, Object>> findByIdTalla(Integer id);
	
	public ResponseEntity<Map<String, Object>> saveTalla(Talla talla);

}
