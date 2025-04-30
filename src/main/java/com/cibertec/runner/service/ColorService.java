package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Color;

public interface ColorService {
	
	
	
	public ResponseEntity<Map<String , Object>> obtenerporId(Integer id);
	
	public ResponseEntity<Map<String, Object>> listarTodos();
	
	public ResponseEntity<Map<String , Object>> agregarColor(Color color);

}
