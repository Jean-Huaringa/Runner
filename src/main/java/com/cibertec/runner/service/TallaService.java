package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Talla;

public interface TallaService {
	
	
	
	public ResponseEntity<Map<String, Object>> listarTodos();
	
	public ResponseEntity<Map<String, Object>> obtenerporId(Integer id);
	
	
	public ResponseEntity<Map<String, Object>> Agregar(Talla talla);

}
