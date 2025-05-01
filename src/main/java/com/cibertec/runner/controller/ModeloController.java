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

import com.cibertec.runner.dto.request.ModeloDTO;
import com.cibertec.runner.service.implement.ModeloServiceImp;

@RestController
@RequestMapping("/api/modelo")
public class ModeloController {
	
	@Autowired
	private ModeloServiceImp modeloService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> findAllModelos(){
		return modeloService.findAllModelos();
	}
	
	@PostMapping
    public ResponseEntity<Map<String, Object>> saveModelo(@RequestBody ModeloDTO modeloDTO) {
        return modeloService.saveModelo(modeloDTO);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateModelo(@RequestBody ModeloDTO modeloDTO, @PathVariable Long id) {
        return modeloService.updateModelo(modeloDTO, id);
    }
	
	@DeleteMapping("/logico/{id}")
	public ResponseEntity<Map<String, Object>> deleteByIdModelo(@PathVariable Long id) {
	    return modeloService.deleteByIdModelo(id);
	}
}