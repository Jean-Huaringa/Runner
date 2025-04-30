package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Categoria;

public interface CategoriaService {

	// LISTADO GENERAL DE CATEGORIAS
	public ResponseEntity<Map<String, Object>> findAllListCategoria();
	
	// BUSQUEDA DE CATEGORIA POR SU ID
	public ResponseEntity<Map<String, Object>> findByIdCategoria(Integer id);
	
	// CREAR CATEGORIA
	public ResponseEntity<Map<String, Object>> saveCategoria(Categoria c);
	
	// ACTUALIZAR CATEGORIA
	public ResponseEntity<Map<String, Object>> updateCategoria(Categoria c, Integer id);
	
	// ELIMINAR CATEGORIA
	public ResponseEntity<Map<String, Object>> deleteByIdCategoria(Integer id);
	
}
