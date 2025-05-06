package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Talla;

public interface TallaService {
	
	public ResponseEntity<SuccessResponse<List<Talla>>> findAllTalla();
	
	public ResponseEntity<SuccessResponse<Talla>> findByIdTalla(Integer id);
	
	public ResponseEntity<SuccessResponse<Talla>> saveTalla(Talla talla);
	
	public ResponseEntity<SuccessResponse<Talla>> updateTallla(Talla talla, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteTalla(Integer id);

}
