package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Marca;

public interface MarcaService {

	public ResponseEntity<SuccessResponse<List<Marca>>> findAllListMarcas();
	
	public ResponseEntity<SuccessResponse<Marca>> findByIdMarca(Integer id);
	
	public ResponseEntity<SuccessResponse<Marca>> saveMarca(Marca m);
	
	public ResponseEntity<SuccessResponse<Marca>> updateMarca(Marca m, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdMarca(Integer id);
}
