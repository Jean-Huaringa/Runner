package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Talla;
import com.cibertec.runner.repository.ITallaRepository;
import com.cibertec.runner.service.TallaService;


@Service
public class TallaServiceImp implements TallaService{
	
	@Autowired
	private ITallaRepository talRepo;

    @Override
    public ResponseEntity<SuccessResponse<List<Talla>>> findAllTalla() {
        List<Talla> tallas = talRepo.findAll(Sort.by("id").ascending());

        if (!tallas.isEmpty()) {
            SuccessResponse<List<Talla>> success = SuccessResponse.<List<Talla>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(tallas)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No existen registros de tallas");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Talla>> findByIdTalla(Integer id) {
        Optional<Talla> talla = talRepo.findById(id);

        if (talla.isPresent()) {
            SuccessResponse<Talla> success = SuccessResponse.<Talla>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(talla.get())
                    .build();

            return ResponseEntity.ok(success);
        } else {
            throw new RuntimeException("No se encuentra un registro para el ID: " + id);
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Talla>> saveTalla(Talla talla) {
        if (talRepo.existsByNombre(talla.getNombre())) {
            throw new RuntimeException("La talla ya existe");
        }

        Talla newTalla = new Talla();
        newTalla.setNombre(talla.getNombre());
        Talla tallaGuardada = talRepo.save(newTalla);

        SuccessResponse<Talla> success = SuccessResponse.<Talla>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(tallaGuardada)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }
}
