package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Persona;
import com.cibertec.runner.repository.IPersonaRepository;
import com.cibertec.runner.service.PersonaService;

import jakarta.persistence.NoResultException;

@Service
public class PersonaServiceImp implements PersonaService{

	@Autowired
	private IPersonaRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<List<Persona>>> findAllPersonas() {
        List<Persona> personas = repository.findAll();

        if (personas.isEmpty()) {
        	throw new NoResultException("No se encontro ningun tipo de persona");
        }
        
        SuccessResponse<List<Persona>> success = SuccessResponse.<List<Persona>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(personas)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Persona>> findByIdPersona(Integer id) {
    	
        Persona persona = repository.findById(id).orElse(null);

        if (persona == null) {
        	throw new NoResultException("No se encontro el codigo del tipo de persona");
        }
        
        SuccessResponse<Persona> success = SuccessResponse.<Persona>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(persona)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Persona>> savePersona(Persona p) {
    	
		if(repository.existsByNombre(p.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
        Persona newPersona = new Persona();
        newPersona.setNombre(p.getNombre());
        Persona personaGuardada = repository.save(newPersona);

        SuccessResponse<Persona> success = SuccessResponse.<Persona>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(personaGuardada)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }
    
    

    @Override
    public ResponseEntity<SuccessResponse<Persona>> updatePersona(Persona p, Integer id) {
    	
        Persona personaExistente = repository.findById(id).orElse(null);

        if (personaExistente == null) {
        	throw new NoResultException("No se encontro el codigo del tipo de persona");
        }

        personaExistente.setNombre(p.getNombre());
        Persona personaActualizada = repository.save(personaExistente);

        SuccessResponse<Persona> success = SuccessResponse.<Persona>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(personaActualizada)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdPersona(Integer id) {
    	
        Persona persona = repository.findById(id).orElse(null);

        if (persona == null) {
        	throw new NoResultException("No se encontro el codigo del tipo de persona");
        }

        repository.delete(persona);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Persona eliminada con éxito")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
	
}
