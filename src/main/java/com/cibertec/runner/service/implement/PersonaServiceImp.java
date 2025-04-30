package com.cibertec.runner.service.implement;

import java.util.List;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.model.Persona;
import com.cibertec.runner.repository.IPersonaRepository;
import com.cibertec.runner.service.PersonaService;

@Service
public class PersonaServiceImp implements PersonaService{

	@Autowired
	private IPersonaRepository dao;

	@Override
	public ResponseEntity<Map<String, Object>> findAllPersonas() {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA DE LISTADO DE PERSONAS
		List<Persona> personas =  dao.findAll();
		
		// VERIFICAR SI LISTA ESTA VACIA
		if(!personas.isEmpty()) {
			// MENSAJE CON ENVIO DE LISTADO
			respuesta.put("mensaje", "Listado de personas");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("personas", personas);
			
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			// MENSAJE SI LA LISTA ESTA VACIA
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> findByIdPersona(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA PERSONA BUSCADA POR ID
		Optional<Persona> persona =  dao.findById(id);
		
		// VERIFICA SI LA PERSONA EXISTE
		if(persona.isPresent()) {
			// MENSAJE CON LA PERSONA ENCONTRADA
			respuesta.put("mensaje", "Persona encontrada");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("persona", persona.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			// MENSAJE SI NO ENCUENTRA LA PERSONA
			respuesta.put("mensaje", "No se encuentran registros con el ID " + id);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> savePersona(Persona p) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
		try {
			// REALIZA REGISTRO
			dao.save(p);
			
			// MENSAJE CON LA PERSONA ENCONTRADA
			respuesta.put("mensaje", "Persona creada");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("persona", p);

			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch (Exception e) {
			// MENSAJE EN CASO DE ERROR EN LA CREACION
			respuesta.put("mensaje", "Error al crear persona");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> updatePersona(Persona p, Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA PERSONA BUSCADA POR ID
		Optional<Persona> buscaPersona =  dao.findById(id);
		
		// VERIFICA SI LA PERSONA YA EXISTE EN LA BD
		if(!buscaPersona.isPresent()) {
			// MENSAJE EN CASO LA PERSONA NO EXISTE
			respuesta.put("mensaje", "La persona con el ID especificado no existe");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
		try {
			// ACTUALIZA VALORES DE LA PERSONA A ACTUALIZAR
			Persona personaActual = buscaPersona.get();
			personaActual.setNombre(p.getNombre());
			
			// CAPTURA PERSONA ACTUALIZADA
			Persona personaActualizada = dao.save(personaActual);
			
			// MENSAJE DE EXITO EN LA ACTUALIZACION
			respuesta.put("mensaje", "Persona actualizada");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("persona", personaActualizada);

			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch (Exception e) {
			// MENSAJE EN CASO DE ERROR
			respuesta.put("mensaje", "Error al actualizar persona");
			respuesta.put("error", e.getMessage());
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> deleteByIdPersona(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA PERSONA BUSCADA POR ID
		Optional<Persona> buscaPersona =  dao.findById(id);
		
		// VERIFICA SI LA PERSONA YA EXISTE EN LA BD
		if(buscaPersona.isPresent()) {
			// ELIMINA LA CATEGORIA
			dao.delete(buscaPersona.get());
			
			// RESPUESTA EN CASO DE EXITO EN LA ELIMIMNACION
			respuesta.put("mensaje", "Persona eliminada con exito");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);

			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			// MENSAJE EN CASO DE ERROR EN LA ELIMINACION
			respuesta.put("mensaje", "Error al eliminar, no se encuentran registros con el ID: " + id);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}
	
}
