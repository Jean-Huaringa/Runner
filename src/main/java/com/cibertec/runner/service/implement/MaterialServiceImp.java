package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Material;
import com.cibertec.runner.repository.IMaterialRepository;
import com.cibertec.runner.service.MaterialService;

@Service
public class MaterialServiceImp implements MaterialService{

    @Autowired
    private IMaterialRepository materialRepo;

    @Override
    public ResponseEntity<SuccessResponse<List<Material>>> findAllMateriales() {
        List<Material> materiales = materialRepo.findAll();

        if (!materiales.isEmpty()) {
            SuccessResponse<List<Material>> success = SuccessResponse.<List<Material>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(materiales)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No se encontraron materiales.");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Material>> findByIdMateriales(Integer id) {
        Material material = materialRepo.findById(id).orElse(null);

        if (material != null) {
            SuccessResponse<Material> success = SuccessResponse.<Material>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(material)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No se encontró el material con ID: " + id);
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Material>> saveMaterial(Material m) {
    	Material mat = new Material();
    	mat.setNombre(m.getNombre());
        Material nuevoMaterial = materialRepo.save(mat);

        SuccessResponse<Material> success = SuccessResponse.<Material>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(nuevoMaterial)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Material>> updateMaterial(Material m, Integer id) {
        Material existente = materialRepo.findById(id).orElse(null);

        if (existente == null) {
            throw new RuntimeException("No existe un material con ID: " + id);
        }

        existente.setNombre(m.getNombre());
        Material actualizado = materialRepo.save(existente);

        SuccessResponse<Material> success = SuccessResponse.<Material>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(actualizado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdMaterial(Integer id) {
        Material material = materialRepo.findById(id).orElse(null);

        if (material == null) {
            throw new RuntimeException("No existe un material con ID: " + id);
        }

        materialRepo.delete(material);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Material eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
