package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.model.Categoria;
import com.cibertec.runner.serviceImp.CategoriaServiceImp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaServiceImp cs;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> list() {
		return cs.listCategorias();
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Map<String, Object>> listById(@PathVariable Integer id) {
		return cs.listCategoriaById(id);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Map<String, Object>> create(@RequestBody Categoria c) {
		return cs.createCategoria(c);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Categoria c, @PathVariable Integer id) {
		return cs.updateCategoria(c, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
		return cs.deleteCategoria(id);
	}
}
