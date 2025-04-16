package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.model.Marca;
import com.cibertec.runner.serviceImp.MarcaServiceImp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/marca")
public class MarcaController {
	
	@Autowired
	private MarcaServiceImp ms;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> list() {
		return ms.listMarcas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listById(@PathVariable Integer id) {
		return ms.listMarcaById(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> create(@RequestBody Marca m) {
		return ms.createMarca(m);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Marca m, @PathVariable Integer id) {
		return ms.updateMarca(m, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
		return ms.deleteMarca(id);
	}
	
}
