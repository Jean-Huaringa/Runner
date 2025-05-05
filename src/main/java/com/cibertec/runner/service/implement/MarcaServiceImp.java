package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Marca;
import com.cibertec.runner.repository.IMarcaRepository;
import com.cibertec.runner.service.MarcaService;

import jakarta.persistence.NoResultException;

@Service
public class MarcaServiceImp implements MarcaService {

    @Autowired
    private IMarcaRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<List<Marca>>> findAllListMarcas() {

        List<Marca> marcas = repository.findAll();

        if (marcas.isEmpty()) {
        	throw new NoResultException("No se encontro ninguna marca");
        }
        
        SuccessResponse<List<Marca>> success = SuccessResponse.<List<Marca>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(marcas)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Marca>> findByIdMarca(Integer id) {

        Marca marca = repository.findById(id).orElse(null);

        if (marca == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }
        
        SuccessResponse<Marca> success = SuccessResponse.<Marca>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(marca)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Marca>> saveMarca(Marca marca) {
    	
		if(repository.existsByNombre(marca.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
    	Marca mar = new Marca();
		mar.setNombre(marca.getNombre());
        Marca nuevaMarca = repository.save(mar);

        SuccessResponse<Marca> success = SuccessResponse.<Marca>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(nuevaMarca)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Marca>> updateMarca(Marca marca, Integer id) {

		if(repository.existsByNombre(marca.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
        Marca existente = repository.findById(id).orElse(null);

        if (existente == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }

        existente.setNombre(marca.getNombre());

        Marca actualizada = repository.save(existente);

        SuccessResponse<Marca> success = SuccessResponse.<Marca>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(actualizada)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdMarca(Integer id) {

        Marca marca = repository.findById(id).orElse(null);

        if (marca == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }
        
        repository.delete(marca);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Marca eliminada correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
