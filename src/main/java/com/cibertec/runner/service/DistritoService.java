package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Distrito;

@Service
public interface DistritoService {

	public ResponseEntity<SuccessResponse<Distrito>> findByIdDistrito(Integer id);
	
	public ResponseEntity<SuccessResponse<List<Distrito>>> findAllDistrito();
	
	public ResponseEntity<SuccessResponse<Distrito>> saveDistrito(Distrito distrito);
}
