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

import com.cibertec.runner.model.Color;
import com.cibertec.runner.service.implement.ColorServiceImp;

@RestController
@RequestMapping("/api/color")
public class ColorController {
	
	@Autowired
	private ColorServiceImp colSer;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> findAllListColor(){
		return colSer.findAllListColor();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> findByIdColor(@PathVariable Integer id){
		return colSer.findByIdColor(id);
	}
	
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> saveColor(@RequestBody Color color) {
        return colSer.saveColor(color);
    }
	
	
	
}
