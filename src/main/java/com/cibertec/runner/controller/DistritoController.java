package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.model.Distrito;
import com.cibertec.runner.service.implement.DistritoServiceImp;

@RestController
@RequestMapping("/api/distrito")
public class DistritoController {
	@Autowired
	private DistritoServiceImp disSer;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> findAllListDistrito(){
		return disSer.findAllListDistrito();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> findByIdDistrito(@PathVariable Integer id){
		return disSer.findByIdDistrito(id);
	}
	
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> saveDistrito(@RequestBody Distrito distrito) {
        return disSer.saveDistrito(distrito);
    }
}
