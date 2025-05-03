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

import com.cibertec.runner.dto.request.ModeloDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Modelo;
import com.cibertec.runner.service.implement.ModeloServiceImp;

@RestController
@RequestMapping("/api/modelo")
public class ModeloController {
	
	@Autowired
	private ModeloServiceImp modeloService;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Modelo>>> findAllModelos(){
		return modeloService.findAllModelos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Modelo>> findByIdModelos(@PathVariable Integer id){
		return modeloService.findByIdModel(id);
	}
	
	@PostMapping
    public ResponseEntity<SuccessResponse<Modelo>> saveModelo(@RequestBody ModeloDTO modeloDTO) {
        return modeloService.saveModelo(modeloDTO);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<Modelo>> updateModelo(@RequestBody ModeloDTO modeloDTO, @PathVariable Integer id) {
        return modeloService.updateModelo(modeloDTO, id);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdModelo(@PathVariable Integer id) {
	    return modeloService.deleteByIdModelo(id);
	}
	
	@GetMapping("/marca/{idMrc}")
	public ResponseEntity<SuccessResponse<List<Modelo>>> findByIdMrc(@PathVariable Integer idMrc) {
	    return modeloService.findByIdMrc(idMrc);
	}
}