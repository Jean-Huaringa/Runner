package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.ModeloDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Modelo;

public interface ModeloService {
	
	public ResponseEntity<SuccessResponse<List<Modelo>>> findAllModelos();

	public ResponseEntity<SuccessResponse<Modelo>> findByIdModel(Integer id);
	
	public ResponseEntity<SuccessResponse<Modelo>> saveModelo(ModeloDTO modeloDTO);
	
	public ResponseEntity<SuccessResponse<Modelo>> updateModelo(ModeloDTO modeloDTO, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdModelo(Integer id);

	public ResponseEntity<SuccessResponse<List<Modelo>>> findByIdMrc(Integer id);

}
