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
import com.cibertec.runner.model.Marca;
import com.cibertec.runner.service.implement.MarcaServiceImp;


@RestController
@RequestMapping("/api/marca")
public class MarcaController {
	
	@Autowired
	private MarcaServiceImp ms;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Marca>>> findAllListMarcas() {
		return ms.findAllListMarcas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Marca>> findByIdMarca(@PathVariable Integer id) {
		return ms.findByIdMarca(id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Marca>> saveMarca(@RequestBody Marca m) {
		return ms.saveMarca(m);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Marca>> updateMarca(@RequestBody Marca m, @PathVariable Integer id) {
		return ms.updateMarca(m, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdMarca(@PathVariable Integer id) {
		return ms.deleteByIdMarca(id);
	}
	
}
