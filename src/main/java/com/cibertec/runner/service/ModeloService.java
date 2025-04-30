package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.ModeloDTO;

public interface ModeloService {
	
	public ResponseEntity<Map<String, Object>> listModelos();
	
	public ResponseEntity<Map<String, Object>> agregaModelo(ModeloDTO modeloDTO);
	
	public ResponseEntity<Map<String, Object>> actualizaModelo(ModeloDTO modeloDTO, Long id);
	
	public ResponseEntity<Map<String, Object>> eliminarLogicoModelo(Long id);
	

}
