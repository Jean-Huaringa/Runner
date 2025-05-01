package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Marca;
import com.cibertec.runner.repository.IMarcaRepository;
import com.cibertec.runner.service.MarcaService;

@Service
public class MarcaServiceImp implements MarcaService {

    @Autowired
    private IMarcaRepository marcaRepo;

    @Override
    public ResponseEntity<SuccessResponse<List<Marca>>> findAllListMarcas() {

        List<Marca> marcas = marcaRepo.findAll();

        if (!marcas.isEmpty()) {
            SuccessResponse<List<Marca>> success = SuccessResponse.<List<Marca>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(marcas)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No se encontraron marcas registradas");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Marca>> findByIdMarca(Integer id) {

        Marca marca = marcaRepo.findById(id).orElse(null);

        if (marca != null) {
            SuccessResponse<Marca> success = SuccessResponse.<Marca>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(marca)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No se encontró una marca con ID: " + id);
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Marca>> saveMarca(Marca marca) {

        Marca nuevaMarca = marcaRepo.save(marca);

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

        Marca existente = marcaRepo.findById(id).orElse(null);

        if (existente == null) {
            throw new RuntimeException("La marca con ID: " + id + " no existe");
        }

        existente.setNombre(marca.getNombre());

        Marca actualizada = marcaRepo.save(existente);

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

        Marca marca = marcaRepo.findById(id).orElse(null);

        if (marca != null) {
            marcaRepo.delete(marca);

            SuccessResponse<String> success = SuccessResponse.<String>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response("Marca eliminada correctamente")
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No se encontró una marca con ID: " + id);
        }
    }
}
