package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Persona;
import com.cibertec.runner.repository.IPersonaRepository;
import com.cibertec.runner.service.PersonaService;

@Service
public class PersonaServiceImp implements PersonaService{

	@Autowired
	private IPersonaRepository dao;

    @Override
    public ResponseEntity<SuccessResponse<List<Persona>>> findAllPersonas() {
        List<Persona> personas = dao.findAll();

        if (!personas.isEmpty()) {
            SuccessResponse<List<Persona>> success = SuccessResponse.<List<Persona>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(personas)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No existen registros");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Persona>> findByIdPersona(Integer id) {
        Persona persona = dao.findById(id).orElse(null);

        if (persona != null) {
            SuccessResponse<Persona> success = SuccessResponse.<Persona>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(persona)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No se encuentran registros con el ID: " + id);
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Persona>> savePersona(Persona p) {
        Persona personaGuardada = dao.save(p);

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
        Persona personaExistente = dao.findById(id).orElse(null);

        if (personaExistente == null) {
            throw new RuntimeException("La persona con el ID especificado no existe");
        }

        personaExistente.setNombre(p.getNombre());
        Persona personaActualizada = dao.save(personaExistente);

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
        Persona persona = dao.findById(id).orElse(null);

        if (persona == null) {
            throw new RuntimeException("Error al eliminar, no se encuentran registros con el ID: " + id);
        }

        dao.delete(persona);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Persona eliminada con éxito")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
	
}
