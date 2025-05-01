package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Material;

public interface MaterialService {

	// LISTADO GENERAL DE MATERIALES
	public ResponseEntity<SuccessResponse<List<Material>>> findAllMateriales();
	
	// BUSQUEDA DE MATERIAL POR SU ID
	public ResponseEntity<SuccessResponse<Material>> findByIdMateriales(Integer id);
	
	// CREAR MATERIAL
	public ResponseEntity<SuccessResponse<Material>> saveMaterial(Material m);
	
	// ACTUALIZAR MATERIAL
	public ResponseEntity<SuccessResponse<Material>> updateMaterial(Material m, Integer id);
	
	// ELIMINAR MATERIAL
	public ResponseEntity<SuccessResponse<String>> deleteByIdMaterial(Integer id);
}
