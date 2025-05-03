package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Distrito;
import com.cibertec.runner.service.implement.DistritoServiceImp;

@RestController
@RequestMapping("/api/distrito")
public class DistritoController {
	@Autowired
	private DistritoServiceImp disSer;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Distrito>>> findAllDistrito(){
		return disSer.findAllDistrito();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Distrito>> findByIdDistrito(@PathVariable Integer id){
		return disSer.findByIdDistrito(id);
	}
	
    @PostMapping
    public ResponseEntity<SuccessResponse<Distrito>> saveDistrito(@RequestBody Distrito distrito) {
        return disSer.saveDistrito(distrito);
    }
}
