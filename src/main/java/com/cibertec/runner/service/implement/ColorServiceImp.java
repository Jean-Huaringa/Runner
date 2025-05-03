package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Color;
import com.cibertec.runner.repository.IColorRepository;
import com.cibertec.runner.service.ColorService;

@Service
public class ColorServiceImp implements ColorService {

	@Autowired
	private IColorRepository colRepo;

	@Override
	public ResponseEntity<SuccessResponse<List<Color>>> findAllColor() {

		List<Color> colores = colRepo.findAll();

		if (!colores.isEmpty()) {

			SuccessResponse<List<Color>> success = SuccessResponse.<List<Color>>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.CREATED.value())
			        .success(HttpStatus.CREATED.getReasonPhrase())
			        .response(colores)
			        .build();
			
			return ResponseEntity.status(HttpStatus.OK).body(success);
		} else {
			throw new RuntimeException("No se encontro ningun color");
		}

	}

	@Override
	public ResponseEntity<SuccessResponse<Color>> findByIdColor(Integer id){
		
		Color color = colRepo.findById(id).orElse(null);

		if (color != null) {
			
			SuccessResponse<Color> success = SuccessResponse.<Color>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.CREATED.value())
			        .success(HttpStatus.CREATED.getReasonPhrase())
			        .response(color)
			        .build();
			
			return ResponseEntity.status(HttpStatus.OK).body(success);
		} else {
			throw new RuntimeException("No se encontro ningun color");
		}
	}

	@Override
	public ResponseEntity<SuccessResponse<Color>> saveColor(Color color){

		Color c = new Color();
		
		c.setNombre(color.getNombre());
		
		Color col = colRepo.save(c);

		SuccessResponse<Color> success = SuccessResponse.<Color>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.CREATED.value())
		        .success(HttpStatus.CREATED.getReasonPhrase())
		        .response(col)
		        .build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

}
