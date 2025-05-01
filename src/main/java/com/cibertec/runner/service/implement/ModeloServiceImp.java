package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.request.ModeloDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Modelo;
import com.cibertec.runner.repository.IModeloRepository;
import com.cibertec.runner.service.ModeloService;

@Service
public class ModeloServiceImp implements ModeloService{
	
	@Autowired
	private IModeloRepository dao;
	

    @Override
    public ResponseEntity<SuccessResponse<List<Modelo>>> findAllModelos() {
        List<Modelo> modelos = dao.findAll();

        if (!modelos.isEmpty()) {
            SuccessResponse<List<Modelo>> success = SuccessResponse.<List<Modelo>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(modelos)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No existen Registros");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Modelo>> saveModelo(ModeloDTO modeloDTO) {
    	
        Modelo modelo = new Modelo();
        modelo.setDescripcion(modeloDTO.getDescripcion());
        modelo.setInfo(modeloDTO.getInfo());
        modelo.setEstado(modeloDTO.getEstado());
        modelo.setPrecio(modeloDTO.getPrecio());
        modelo.setIdCtg(modeloDTO.getIdCtg());
        modelo.setIdMrc(modeloDTO.getIdMrc());
        modelo.setIdPrn(modeloDTO.getIdPrn());
        modelo.setIdMtl(modeloDTO.getIdMtl());

        Modelo modeloGuardado = dao.save(modelo);

        SuccessResponse<Modelo> success = SuccessResponse.<Modelo>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(modeloGuardado)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Modelo>> updateModelo(ModeloDTO modeloDTO, Long id) {

        Optional<Modelo> modEncontrada = dao.findById(id);

        if (modEncontrada.isPresent()) {
            Modelo modelo = modEncontrada.get();

            modelo.setDescripcion(modeloDTO.getDescripcion());
            modelo.setInfo(modeloDTO.getInfo());
            modelo.setEstado(modeloDTO.getEstado());
            modelo.setPrecio(modeloDTO.getPrecio());
            modelo.setIdCtg(modeloDTO.getIdCtg());
            modelo.setIdMrc(modeloDTO.getIdMrc());
            modelo.setIdPrn(modeloDTO.getIdPrn());
            modelo.setIdMtl(modeloDTO.getIdMtl());

            Modelo modeloActualizado = dao.save(modelo);

            SuccessResponse<Modelo> success = SuccessResponse.<Modelo>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(modeloActualizado)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("Modelo no encontrado");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdModelo(Long id) {
        Optional<Modelo> modEncontrado = dao.findById(id);

        if (modEncontrado.isPresent()) {
            Modelo mod = modEncontrado.get();
            mod.setEstado(false);
            dao.save(mod);

            SuccessResponse<String> success = SuccessResponse.<String>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.NO_CONTENT.value())
                    .success(HttpStatus.NO_CONTENT.getReasonPhrase())
                    .response("Modelo eliminado correctamente")
                    .build();

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success);
        } else {
            throw new RuntimeException("Sin registros para el ID: " + id);
        }
    }
}
