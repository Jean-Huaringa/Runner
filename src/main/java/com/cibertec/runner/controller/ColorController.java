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

import com.cibertec.runner.dto.TicketDTO;
import com.cibertec.runner.model.Color;
import com.cibertec.runner.serviceImpl.ColorServiceImpl;

@RestController
@RequestMapping("/api/color")
public class ColorController {
	
	
	@Autowired
	private ColorServiceImpl colSer;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> listTickets(){
		return colSer.listarTodos();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> buscarporId(@PathVariable Integer id){
		return colSer.obtenerporId(id);
	}
	
	  // Registrar
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody Color color) {
        return colSer.agregarColor(color);
    }
	
	
	
}
