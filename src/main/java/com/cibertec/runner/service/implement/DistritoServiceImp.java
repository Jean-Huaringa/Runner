package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Distrito;
import com.cibertec.runner.repository.IDistritoRepository;
import com.cibertec.runner.service.DistritoService;

@Service
public class DistritoServiceImp implements DistritoService {

    @Autowired
    private IDistritoRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<Distrito>> findByIdDistrito(Integer id) {
        Distrito distrito = repository.findById(id).orElse(null);

        if (distrito != null) {
            SuccessResponse<Distrito> success = SuccessResponse.<Distrito>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(distrito)
                .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No se encuentra un registro para el ID: " + id);
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<List<Distrito>>> findAllDistrito() {
        List<Distrito> distritos = repository.findAll();

        if (!distritos.isEmpty()) {
            SuccessResponse<List<Distrito>> success = SuccessResponse.<List<Distrito>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(distritos)
                .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No existen registros de distrito");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Distrito>> saveDistrito(Distrito distrito) {
        if (repository.existsByNombre(distrito.getNombre())) {
            throw new RuntimeException("El distrito ya existe");
        }

        Distrito savedDistrito = repository.save(distrito);

        SuccessResponse<Distrito> success = SuccessResponse.<Distrito>builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.CREATED.value())
            .success(HttpStatus.CREATED.getReasonPhrase())
            .response(savedDistrito)
            .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }
}
