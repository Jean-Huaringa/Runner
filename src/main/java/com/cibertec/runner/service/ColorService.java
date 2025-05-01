package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Color;

public interface ColorService {
	
	
	public ResponseEntity<SuccessResponse<List<Color>>> findAllColor();
	
	public ResponseEntity<SuccessResponse<Color>> findByIdColor(Integer id);
	
	public ResponseEntity<SuccessResponse<Color>> saveColor(Color color);

}
