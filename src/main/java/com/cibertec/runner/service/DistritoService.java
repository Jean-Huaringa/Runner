package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.model.Distrito;

@Service
public interface DistritoService {

	public ResponseEntity<Map<String , Object>> findByIdDistrito(Integer id);
	
	public ResponseEntity<Map<String, Object>> findAllListDistrito();
	
	public ResponseEntity<Map<String , Object>> saveDistrito(Distrito distrito);
}
