package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.model.Material;
import com.cibertec.runner.service.implement.MaterialServiceImp;

@RestController
@RequestMapping("/api/material")
public class MaterialController {
	
	@Autowired
	private MaterialServiceImp ms;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> findAllMateriales() {
		return ms.findAllMateriales();
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Map<String, Object>> findByIdMateriales(@PathVariable Integer id) {
		return ms.findByIdMateriales(id);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Map<String, Object>> saveMaterial(@RequestBody Material m) {
		return ms.saveMaterial(m);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, Object>> updateMaterial(@RequestBody Material m, @PathVariable Integer id) {
		return ms.updateMaterial(m, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> deleteByIdMaterial(@PathVariable Integer id) {
		return ms.deleteByIdMaterial(id);
	}
}
