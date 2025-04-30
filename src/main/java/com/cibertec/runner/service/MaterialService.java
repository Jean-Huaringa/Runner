package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Material;

public interface MaterialService {

	// LISTADO GENERAL DE MATERIALES
	public ResponseEntity<Map<String, Object>> listMateriales();
	
	// BUSQUEDA DE MATERIAL POR SU ID
	public ResponseEntity<Map<String, Object>> listMaterialById(Integer id);
	
	// CREAR MATERIAL
	public ResponseEntity<Map<String, Object>> createMaterial(Material m);
	
	// ACTUALIZAR MATERIAL
	public ResponseEntity<Map<String, Object>> updateMaterial(Material m, Integer id);
	
	// ELIMINAR MATERIAL
	public ResponseEntity<Map<String, Object>> deleteMaterial(Integer id);
}
