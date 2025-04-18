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
import com.cibertec.runner.serviceImp.MaterialServiceImp;

@RestController
@RequestMapping("/api/material")
public class MaterialController {
	
	@Autowired
	private MaterialServiceImp ms;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> list() {
		return ms.listMateriales();
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Map<String, Object>> listById(@PathVariable Integer id) {
		return ms.listMaterialById(id);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Map<String, Object>> create(@RequestBody Material m) {
		return ms.createMaterial(m);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Material m, @PathVariable Integer id) {
		return ms.updateMaterial(m, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
		return ms.deleteMaterial(id);
	}
}
