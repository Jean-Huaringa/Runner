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

import com.cibertec.runner.model.Talla;
import com.cibertec.runner.service.implement.TallaServiceImp;

@RestController
@RequestMapping("/api/talla")
public class TallaController {

	@Autowired
	private TallaServiceImp talRepo;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> findAllTalla(){
		return talRepo.findAllTalla();
	}
	
	// busqueda por su primary key del objeto, en este caso el objeto es la talla 
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> findByIdTalla(@PathVariable Integer id){
		return talRepo.findByIdTalla(id);
	}
	
	  // Registrar
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> saveTalla(@RequestBody Talla talla) {
        return talRepo.saveTalla(talla);
    }
	
}
