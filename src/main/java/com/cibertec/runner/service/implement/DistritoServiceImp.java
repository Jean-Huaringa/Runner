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

import com.cibertec.runner.model.Distrito;
import com.cibertec.runner.repository.IDistritoRepository;
import com.cibertec.runner.service.DistritoService;

@Service
public class DistritoServiceImp implements DistritoService{
	@Autowired
	private IDistritoRepository repository;
	
	@Override
	public ResponseEntity<Map<String, Object>> findByIdDistrito(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		Optional<Distrito> distrito = repository.findById(id);

		if (distrito.isPresent()) {
			respuesta.put("mensaje", "Color Encontrada");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("color", distrito.get());
			return ResponseEntity.ok().body(respuesta);
		} else {
			respuesta.put("mensaje", "No se encuentra un registro para el ID: " + id);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAllListDistrito() {
		Map<String, Object> respuesta = new LinkedHashMap<>();

		List<Distrito> distrito = repository.findAll(Sort.by("id").ascending());

		if (!distrito.isEmpty()) {
			respuesta.put("mensaje", "Lista de Colores");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("Colores", distrito);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> saveDistrito(Distrito distrito) {
		Map<String, Object> respuesta = new LinkedHashMap<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String fechaActual = LocalDateTime.now().format(formatter);

		// valida que el color no se repita y lo compara con el parametro de entrada en
		// este caso para el ombre del color
		if (repository.existsByNombre(distrito.getNombre())) {
			respuesta.put("mensaje", "El Color ya existe");
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.CONFLICT);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
		}

		// guarda todo el objeto color, osea lo registra
		repository.save(distrito);
		respuesta.put("mensaje", "Se creó correctamente el Color");
		respuesta.put("fecha", fechaActual);
		respuesta.put("status", HttpStatus.CREATED);
		respuesta.put("color", distrito);

		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}
}
