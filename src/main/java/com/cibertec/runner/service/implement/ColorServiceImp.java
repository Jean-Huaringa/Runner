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

import com.cibertec.runner.model.Color;
import com.cibertec.runner.repository.IColorRepository;
import com.cibertec.runner.service.ColorService;


@Service
public class ColorServiceImp implements ColorService{

	
	
	@Autowired
	private IColorRepository colRepo;

	@Override
	public ResponseEntity<Map<String, Object>> findAllListColor() {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
		List<Color> colores = colRepo.findAll(Sort.by("id").ascending());
	
	
	
	if (!colores.isEmpty()) {
		respuesta.put("mensaje", "Lista de Colores");
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.OK);
		respuesta.put("Colores", colores);
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	} else {
		respuesta.put("mensaje", "No existen registros");
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	}

	}
	
	
	
	@Override
	public ResponseEntity<Map<String, Object>> findByIdColor(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		Optional<Color> color = colRepo.findById(id);

	    if (color.isPresent()) {
	        respuesta.put("mensaje", "Color Encontrada");
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.OK);
	        respuesta.put("color", color.get());
	        return ResponseEntity.ok().body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No se encuentra un registro para el ID: " + id);
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> saveColor(Color color) {

	    Map<String, Object> respuesta = new LinkedHashMap<>();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);

	    // valida que el color no se repita y lo compara con el parametro de entrada en este caso para el ombre del color 
	    if (colRepo.existsByNombre(color.getNombre())) {
	        respuesta.put("mensaje", "El Color ya existe");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.CONFLICT);
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
	    }

	    
	    //guarda todo el objeto color, osea lo registra
	    colRepo.save(color);
	    respuesta.put("mensaje", "Se creó correctamente el Color");
	    respuesta.put("fecha", fechaActual);
	    respuesta.put("status", HttpStatus.CREATED);
	    respuesta.put("color", color); 

	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

		
	
	
	
	
	
	
	
	
	
	
	

}
