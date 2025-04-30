package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.model.ModeloDTO;
import com.cibertec.runner.serviceImpl.ModeloServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/modelo")
public class ModeloController {
	
	@Autowired
	private ModeloServiceImpl modeloService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listModelos(){
		return modeloService.listModelos();
	}
	
	@PostMapping
    public ResponseEntity<Map<String, Object>> agregarModelo(@RequestBody ModeloDTO modeloDTO) {
        return modeloService.agregaModelo(modeloDTO);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarModelo(@RequestBody ModeloDTO modeloDTO, @PathVariable Long id) {
        return modeloService.actualizaModelo(modeloDTO, id);
    }
	
	@DeleteMapping("/logico/{id}")
	public ResponseEntity<Map<String, Object>> eliminarLogico(@PathVariable Long id) {
	    return modeloService.eliminarLogicoModelo(id);
	}
}