package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Talla;
import com.cibertec.runner.repository.ITallaRepository;
import com.cibertec.runner.service.TallaService;

import jakarta.persistence.NoResultException;


@Service
public class TallaServiceImp implements TallaService{
	
	@Autowired
	private ITallaRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<List<Talla>>> findAllTalla() {
        List<Talla> tallas = repository.findAll(Sort.by("id").ascending());

        if (tallas.isEmpty()) {
        	throw new NoResultException("No se encontro ninguna talla");
        }
        
        SuccessResponse<List<Talla>> success = SuccessResponse.<List<Talla>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(tallas)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Talla>> findByIdTalla(Integer id) {
        Talla talla = repository.findById(id).orElse(null);

        if (talla == null) {
        	throw new NoResultException("No se encontro el codigo de la talla");
        }
        
        SuccessResponse<Talla> success = SuccessResponse.<Talla>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(talla)
                .build();

        return ResponseEntity.ok(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Talla>> saveTalla(Talla talla) {
    	
    	if(repository.existsByNombre(talla.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
        

        Talla newTalla = new Talla();
        newTalla.setNombre(talla.getNombre());
        Talla tallaGuardada = repository.save(newTalla);

        SuccessResponse<Talla> success = SuccessResponse.<Talla>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(tallaGuardada)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

	@Override
	public ResponseEntity<SuccessResponse<Talla>> updateTallla(Talla talla, Integer id) {
		if(repository.existsByNombre(talla.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Talla existente = repository.findById(id).orElse(null);

        if (existente == null) {
        	throw new NoResultException("No se encontro el codigo de material");
        }

        existente.setNombre(talla.getNombre());
        
        Talla actualizado = repository.save(existente);

        SuccessResponse<Talla> success = SuccessResponse.<Talla>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(actualizado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteTalla(Integer id) {
		Talla talla = repository.findById(id).orElse(null);

        if (talla == null) {
        	throw new NoResultException("No se encontro el codigo de el distrito");
        }
        
        repository.delete(talla);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Distrito eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}
}
