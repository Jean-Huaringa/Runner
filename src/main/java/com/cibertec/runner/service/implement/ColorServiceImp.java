package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Color;
import com.cibertec.runner.repository.IColorRepository;
import com.cibertec.runner.service.ColorService;

import jakarta.persistence.NoResultException;

@Service
public class ColorServiceImp implements ColorService {

	@Autowired
	private IColorRepository repository;

	@Override
	public ResponseEntity<SuccessResponse<List<Color>>> findAllColor() {

		List<Color> colores = repository.findAll();

		if (colores.isEmpty()) {
			throw new NoResultException("No se encontro ningun color");
		}
		
		SuccessResponse<List<Color>> success = SuccessResponse.<List<Color>>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(colores)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);

	}

	@Override
	public ResponseEntity<SuccessResponse<Color>> findByIdColor(Integer id){
		
		Color color = repository.findById(id).orElse(null);

		if (color == null) {
			throw new NoResultException("No se encontro el codigo de el color");
		}
		
		SuccessResponse<Color> success = SuccessResponse.<Color>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(color)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<Color>> saveColor(Color color){
		
		if(repository.existsByNombre(color.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Color c = new Color();
		
		c.setNombre(color.getNombre());
		
		Color col = repository.save(c);

		SuccessResponse<Color> success = SuccessResponse.<Color>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.CREATED.value())
		        .success(HttpStatus.CREATED.getReasonPhrase())
		        .response(col)
		        .build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

}
