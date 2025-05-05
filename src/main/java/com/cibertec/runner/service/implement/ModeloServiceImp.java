package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.runner.dto.request.FiltradoModeloDTO;
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
        modelo.setEstado(true);
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
    public ResponseEntity<SuccessResponse<Modelo>> updateModelo(ModeloDTO modeloDTO, Integer id) {

        Optional<Modelo> modEncontrada = dao.findById(id);

        if (modEncontrada.isPresent()) {
            Modelo modelo = modEncontrada.get();

            modelo.setDescripcion(modeloDTO.getDescripcion());
            modelo.setInfo(modeloDTO.getInfo());
            modelo.setEstado(true);
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
    public ResponseEntity<SuccessResponse<String>> deleteByIdModelo(Integer id) {
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

	@Override
	public ResponseEntity<SuccessResponse<Modelo>> findByIdModel(Integer id) {
		Modelo modEncontrado = dao.findById(id).orElse(null);
		if (modEncontrado != null) {
			SuccessResponse<Modelo> success = SuccessResponse.<Modelo>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.OK.value())
			        .success(HttpStatus.OK.getReasonPhrase())
			        .response(modEncontrado)
			        .build();
			
			return ResponseEntity.status(HttpStatus.OK).body(success);
		} else {
			throw new RuntimeException("Problema al buscar el modelo");
		}
	}

	@Override
	public ResponseEntity<SuccessResponse<List<Modelo>>> findByIdMrc(Integer id) {
		List<Modelo> modelos = dao.findByIdMrc(id);

        if (!modelos.isEmpty()) {
            SuccessResponse<List<Modelo>> success = SuccessResponse.<List<Modelo>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(modelos)
                    .build();

            return ResponseEntity.ok(success);
        } else {
            throw new RuntimeException("No se encontraron modelos para la marca con ID: " + id);
        }
	}
	
    @Transactional
    public ResponseEntity<SuccessResponse<List<Modelo>>> findByAttributes(FiltradoModeloDTO filtro) {

        String idClrCsv = listToCsv(filtro.getIdClr());
        String idTllCsv = listToCsv(filtro.getIdTll());
        String idCtgCsv = listToCsv(filtro.getIdCtg());
        String idMrcCsv = listToCsv(filtro.getIdMrc());
        String idPrnCsv = listToCsv(filtro.getIdPrn());
        String idMtlCsv = listToCsv(filtro.getIdMtl());
        
        System.out.println(filtro.getIdClr().toString());

        List<Modelo> productos = dao.filtrarModelos(
            idClrCsv, 
            idTllCsv, 
            idCtgCsv, 
            idMrcCsv, 
            idPrnCsv, 
            idMtlCsv
        );;
        

        if (!productos.isEmpty()) {
            SuccessResponse<List<Modelo>> success = SuccessResponse.<List<Modelo>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(productos)
                    .build();

            return ResponseEntity.ok(success);
        } else {
            throw new IllegalArgumentException("No se encontraron modelos para el filtro enviado ");
        }
    }

    private String listToCsv(List<Integer> list) {
        return (list == null || list.isEmpty()) ? null : list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}
