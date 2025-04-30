package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.model.Marca;
import com.cibertec.runner.service.implement.MarcaServiceImp;

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
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> findAllListMarcas() {
		return ms.findAllListMarcas();
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Map<String, Object>> findByIdMarca(@PathVariable Integer id) {
		return ms.findByIdMarca(id);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Map<String, Object>> saveMarca(@RequestBody Marca m) {
		return ms.saveMarca(m);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, Object>> updateMarca(@RequestBody Marca m, @PathVariable Integer id) {
		return ms.updateMarca(m, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> deleteByIdMarca(@PathVariable Integer id) {
		return ms.deleteByIdMarca(id);
	}
	
}
