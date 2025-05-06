package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.runner.dto.request.FiltradoModeloDTO;
import com.cibertec.runner.dto.request.ModeloDTO;
import com.cibertec.runner.dto.response.ModeloProductoResponse;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Modelo;
import com.cibertec.runner.model.Producto;
import com.cibertec.runner.repository.IModeloRepository;
import com.cibertec.runner.repository.IProductoRepository;
import com.cibertec.runner.service.ModeloService;

import jakarta.persistence.NoResultException;

@Service
public class ModeloServiceImp implements ModeloService{

	@Autowired
	private IModeloRepository dao;
	@Autowired
	private IProductoRepository repositoryProducto;
	

    @Override
    public ResponseEntity<SuccessResponse<List<Modelo>>> findAllModelos() {
        List<Modelo> modelos = dao.findAll();

        if (modelos.isEmpty()) {
        	throw new NoResultException("No se encontro ningun modelo");
        }
        
        SuccessResponse<List<Modelo>> success = SuccessResponse.<List<Modelo>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(modelos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
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

        Modelo modEncontrada = dao.findById(id).orElse(null);

        if (modEncontrada == null) {
        	throw new NoResultException("No se encontro el codigo del modelo");
        } 

        modEncontrada.setDescripcion(modeloDTO.getDescripcion());
        modEncontrada.setInfo(modeloDTO.getInfo());
        modEncontrada.setEstado(true);
        modEncontrada.setPrecio(modeloDTO.getPrecio());
        modEncontrada.setIdCtg(modeloDTO.getIdCtg());
        modEncontrada.setIdMrc(modeloDTO.getIdMrc());
        modEncontrada.setIdPrn(modeloDTO.getIdPrn());
        modEncontrada.setIdMtl(modeloDTO.getIdMtl());

        Modelo modeloActualizado = dao.save(modEncontrada);

        SuccessResponse<Modelo> success = SuccessResponse.<Modelo>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(modeloActualizado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdModelo(Integer id) {
        Modelo modEncontrado = dao.findById(id).orElse(null);

        if (modEncontrado == null) {
        	throw new NoResultException("No se encontro el codigo del modelo");
        }        	
        modEncontrado.setEstado(false);
        
        dao.save(modEncontrado);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NO_CONTENT.value())
                .success(HttpStatus.NO_CONTENT.getReasonPhrase())
                .response("Modelo eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success);
    }

	@Override
	public ResponseEntity<SuccessResponse<Modelo>> findByIdModel(Integer id) {
		Modelo modEncontrado = dao.findById(id).orElse(null);
		if (modEncontrado == null) {
        	throw new NoResultException("No se encontro el codigo del modelo");
		}
		
		SuccessResponse<Modelo> success = SuccessResponse.<Modelo>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(modEncontrado)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
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
	
	@Override
	@Transactional
    public ResponseEntity<SuccessResponse<List<Modelo>>> findByAttributes(FiltradoModeloDTO filtro) {

        String idClrCsv = listToCsv(filtro.getIdClr());
        String idTllCsv = listToCsv(filtro.getIdTll());
        String idCtgCsv = listToCsv(filtro.getIdCtg());
        String idMrcCsv = listToCsv(filtro.getIdMrc());
        String idPrnCsv = listToCsv(filtro.getIdPrn());
        String idMtlCsv = listToCsv(filtro.getIdMtl());

        List<Modelo> productos = dao.filtrarModelos(
            idClrCsv, 
            idTllCsv, 
            idCtgCsv, 
            idMrcCsv, 
            idPrnCsv, 
            idMtlCsv
        );;
        

        if (productos.isEmpty()) {
            throw new NoResultException("No se encontraron modelos para el filtro enviado ");
        }
        
        SuccessResponse<List<Modelo>> success = SuccessResponse.<List<Modelo>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(productos)
                .build();

        return ResponseEntity.ok(success);
        
    }
	
	@Override
	public ResponseEntity<SuccessResponse<ModeloProductoResponse>> findProductosByModelo(Integer id) {

		Modelo modEncontrado = dao.findById(id).orElse(null);
		
		if (modEncontrado == null) {
        	throw new NoResultException("No se encontro el codigo del modelo");
		}
		
        List<Producto> productos = repositoryProducto.findByIdMdl(modEncontrado.getId());
        
        if(productos.isEmpty()) {
        	throw new NoResultException("No se encontro ningun producto registrado con ese modelo");
        }
        
        ModeloProductoResponse mpResponse = new ModeloProductoResponse();
        mpResponse.setId(modEncontrado.getId());
        mpResponse.setDescripcion(modEncontrado.getDescripcion());
        mpResponse.setInfo(modEncontrado.getInfo());
        mpResponse.setEstado(modEncontrado.getEstado());
        mpResponse.setPrecio(modEncontrado.getPrecio());
        mpResponse.setIdCtg(modEncontrado.getIdCtg());
        mpResponse.setIdMrc(modEncontrado.getIdMrc());
        mpResponse.setIdPrn(modEncontrado.getIdPrn());
        mpResponse.setIdMtl(modEncontrado.getIdMtl());
        mpResponse.setCategoria(modEncontrado.getCategoria());
        mpResponse.setMarca(modEncontrado.getMarca());
        mpResponse.setPersona(modEncontrado.getPersona());
        mpResponse.setMaterial(modEncontrado.getMaterial());
        mpResponse.setProductos(productos);
        
            SuccessResponse<ModeloProductoResponse> success = SuccessResponse.<ModeloProductoResponse>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(mpResponse)
                    .build();

            return ResponseEntity.ok(success);

    }

    private String listToCsv(List<Integer> list) {
        return (list == null || list.isEmpty()) ? null : list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}
