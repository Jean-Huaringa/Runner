package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Color;

public interface ColorService {
	
	public ResponseEntity<Map<String , Object>> findByIdColor(Integer id);
	
	public ResponseEntity<Map<String, Object>> findAllListColor();
	
	public ResponseEntity<Map<String , Object>> saveColor(Color color);

}
