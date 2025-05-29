package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Categoria;

public interface CategoriaService {

	public ResponseEntity<SuccessResponse<List<Categoria>>> findAllListCategoria();
	
	public ResponseEntity<SuccessResponse<Categoria>> findByIdCategoria(Integer id);
	
	public ResponseEntity<SuccessResponse<Categoria>> saveCategoria(Categoria c);
	
	public ResponseEntity<SuccessResponse<Categoria>> updateCategoria(Categoria c, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdCategoria(Integer id);
	
}
