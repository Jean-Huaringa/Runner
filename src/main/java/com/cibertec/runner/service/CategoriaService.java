package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Categoria;

public interface CategoriaService {

	// LISTADO GENERAL DE CATEGORIAS
	public ResponseEntity<Map<String, Object>> listCategorias();
	
	// BUSQUEDA DE CATEGORIA POR SU ID
	public ResponseEntity<Map<String, Object>> listCategoriaById(Integer id);
	
	// CREAR CATEGORIA
	public ResponseEntity<Map<String, Object>> createCategoria(Categoria c);
	
	// ACTUALIZAR CATEGORIA
	public ResponseEntity<Map<String, Object>> updateCategoria(Categoria c, Integer id);
	
	// ELIMINAR CATEGORIA
	public ResponseEntity<Map<String, Object>> deleteCategoria(Integer id);
	
}
