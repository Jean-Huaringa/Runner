package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.ModeloDTO;

public interface ModeloService {
	
	public ResponseEntity<Map<String, Object>> findAllModelos();
	
	public ResponseEntity<Map<String, Object>> saveModelo(ModeloDTO modeloDTO);
	
	public ResponseEntity<Map<String, Object>> updateModelo(ModeloDTO modeloDTO, Long id);
	
	public ResponseEntity<Map<String, Object>> deleteByIdModelo(Long id);
	

}
