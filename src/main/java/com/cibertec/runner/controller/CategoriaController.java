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
import com.cibertec.runner.model.Categoria;
import com.cibertec.runner.service.implement.CategoriaServiceImp;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaServiceImp cs;

	@GetMapping("/listado")
	public ResponseEntity<SuccessResponse<List<Categoria>>> findAllListCategoria() {
		return cs.findAllListCategoria();
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<SuccessResponse<Categoria>> findByIdCategoria(@PathVariable Integer id) {
		return cs.findByIdCategoria(id);
	}

	@PostMapping("/crear")
	public ResponseEntity<SuccessResponse<Categoria>> saveCategoria(@RequestBody Categoria c) {
		return cs.saveCategoria(c);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<SuccessResponse<Categoria>> updateCategoria(@RequestBody Categoria c,
			@PathVariable Integer id) {
		return cs.updateCategoria(c, id);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdCategoria(@PathVariable Integer id) {
		return cs.deleteByIdCategoria(id);
	}
}
