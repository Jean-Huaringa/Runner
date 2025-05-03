package com.cibertec.runner.controller;

import java.util.List;

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

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Material;
import com.cibertec.runner.service.implement.MaterialServiceImp;

@RestController
@RequestMapping("/api/material")
public class MaterialController {
	
	@Autowired
	private MaterialServiceImp ms;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Material>>> findAllMateriales() {
		return ms.findAllMateriales();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Material>> findByIdMateriales(@PathVariable Integer id) {
		return ms.findByIdMateriales(id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Material>> saveMaterial(@RequestBody Material m) {
		return ms.saveMaterial(m);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Material>> updateMaterial(@RequestBody Material m, @PathVariable Integer id) {
		return ms.updateMaterial(m, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdMaterial(@PathVariable Integer id) {
		return ms.deleteByIdMaterial(id);
	}
}
