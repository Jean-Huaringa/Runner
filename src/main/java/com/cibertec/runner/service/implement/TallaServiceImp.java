package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.model.Talla;
import com.cibertec.runner.repository.ITallaRepository;
import com.cibertec.runner.service.TallaService;


@Service
public class TallaServiceImp implements TallaService{
	
	@Autowired
	private ITallaRepository talRepo;

	
	
	@Override
	public ResponseEntity<Map<String, Object>> findAllTalla() {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		 
		
		// el codigo dentro del findAll se encarga de ordenar los datos por el numero por su id
		List<Talla> tallas = talRepo.findAll(Sort.by("id").ascending());

	if (!tallas.isEmpty()) {
		respuesta.put("mensaje", "Lista de Tallas");
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.OK);
		respuesta.put("Tallas", tallas);
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	} else {
		respuesta.put("mensaje", "No existen registros");
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	}

	}

	@Override
	public ResponseEntity<Map<String, Object>> findByIdTalla(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		Optional<Talla> talla = talRepo.findById(id);

	    if (talla.isPresent()) {
	        respuesta.put("mensaje", "Talla Encontrado");
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.OK);
	        respuesta.put("talla", talla.get());
	        return ResponseEntity.ok().body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No se encuentra un registro para el ID: " + id);
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> saveTalla(Talla talla) {
	    Map<String, Object> respuesta = new LinkedHashMap<>();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);

	    // valida que la talla no se repita y lo compara con el parametro de entrada en este caso para el nombre del la talla 
	    if (talRepo.existsByNombre(talla.getNombre())) {
	        respuesta.put("mensaje", "La talla ya existe");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.CONFLICT);
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
	    }

	    
	    //guarda todo el objeto talla, osea lo registra
	    talRepo.save(talla);
	    respuesta.put("mensaje", "Se creó correctamente la talla");
	    respuesta.put("fecha", fechaActual);
	    respuesta.put("status", HttpStatus.CREATED);
	    respuesta.put("talla", talla); 

	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

}
