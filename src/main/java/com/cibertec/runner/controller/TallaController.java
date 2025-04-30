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
import com.cibertec.runner.model.Talla;
import com.cibertec.runner.serviceImpl.ColorServiceImpl;
import com.cibertec.runner.serviceImpl.TallaServiceImpl;

@RestController
@RequestMapping("/api/talla")
public class TallaController {
	
	
	
	@Autowired
	private TallaServiceImpl talRepo;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> listTickets(){
		return talRepo.listarTodos();
	}
	
	// busqueda por su primary key del objeto, en este caso el objeto es la talla 
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> buscarporId(@PathVariable Integer id){
		return talRepo.obtenerporId(id);
	}
	
	  // Registrar
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody Talla talla) {
        return talRepo.Agregar(talla);
    }
	
}
