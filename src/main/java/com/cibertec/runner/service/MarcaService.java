package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Marca;

public interface MarcaService {

	// LISTADO GENERAL DE MARCAS
	public ResponseEntity<SuccessResponse<List<Marca>>> findAllListMarcas();
	
	// BUSQUEDA DE MARCA POR ID
	public ResponseEntity<SuccessResponse<Marca>> findByIdMarca(Integer id);
	
	// CREAR MARCA
	public ResponseEntity<SuccessResponse<Marca>> saveMarca(Marca m);
	
	// ACTUALIZAR MARCA
	public ResponseEntity<SuccessResponse<Marca>> updateMarca(Marca m, Integer id);
	
	// ELIMINAR MARCA
	public ResponseEntity<SuccessResponse<String>> deleteByIdMarca(Integer id);
}
