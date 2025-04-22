package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Marca;

public interface MarcaService {

	// LISTADO GENERAL DE MARCAS
	public ResponseEntity<Map<String, Object>> listMarcas();
	
	// BUSQUEDA DE MARCA POR ID
	public ResponseEntity<Map<String, Object>> listMarcaById(Integer id);
	
	// CREAR MARCA
	public ResponseEntity<Map<String, Object>> createMarca(Marca m);
	
	// ACTUALIZAR MARCA
	public ResponseEntity<Map<String, Object>> updateMarca(Marca m, Integer id);
	
	// ELIMINAR MARCA
	public ResponseEntity<Map<String, Object>> deleteMarca(Integer id);
}
