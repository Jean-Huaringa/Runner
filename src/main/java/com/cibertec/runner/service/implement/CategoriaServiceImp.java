package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Categoria;
import com.cibertec.runner.repository.ICategoriaRepository;
import com.cibertec.runner.service.CategoriaService;

@Service
public class CategoriaServiceImp implements CategoriaService {

	@Autowired
	private ICategoriaRepository dao;

	@Override
	public ResponseEntity<SuccessResponse<List<Categoria>>> findAllListCategoria() {
		List<Categoria> categorias = dao.findAll();

		if (!categorias.isEmpty()) {
			SuccessResponse<List<Categoria>> success = SuccessResponse.<List<Categoria>>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.CREATED.value())
			        .success(HttpStatus.CREATED.getReasonPhrase())
			        .response(categorias)
			        .build();
			
			return ResponseEntity.status(HttpStatus.OK).body(success);
		} else {
			throw new RuntimeException("No se encontro ningun producto");
		}
	}

	@Override
	public ResponseEntity<SuccessResponse<Categoria>> findByIdCategoria(Integer id) {
		Categoria categoria = dao.findById(id).orElse(null);

		if (categoria != null) {
			SuccessResponse<Categoria> success = SuccessResponse.<Categoria>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.CREATED.value())
			        .success(HttpStatus.CREATED.getReasonPhrase())
			        .response(categoria)
			        .build();
			
			return ResponseEntity.status(HttpStatus.OK).body(success);
		} else {
			throw new RuntimeException("Problema al buscar el producto");
		}
	}

	@Override
	public ResponseEntity<SuccessResponse<Categoria>> saveCategoria(Categoria c) {

		try {
			Categoria cate = dao.save(c);
			SuccessResponse<Categoria> success = SuccessResponse.<Categoria>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.CREATED.value())
			        .success(HttpStatus.CREATED.getReasonPhrase())
			        .response(cate)
			        .build();
			
			return ResponseEntity.status(HttpStatus.OK).body(success);
		} catch (Exception e) {
			throw new RuntimeException("Problema al registrar el producto");
		}
	}

	@Override
	public ResponseEntity<SuccessResponse<Categoria>> updateCategoria(Categoria c, Integer id) {
		
		Categoria categoria = dao.findById(id).orElse(null);

		if (categoria == null) {
			throw new RuntimeException("Problema al buscar el producto");
		}

		categoria.setNombre(c.getNombre());
		
		Categoria update = dao.save(categoria);
		
		SuccessResponse<Categoria> success = SuccessResponse.<Categoria>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.CREATED.value())
		        .success(HttpStatus.CREATED.getReasonPhrase())
		        .response(update)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteByIdCategoria(Integer id) {
		Categoria buscaCategoria = dao.findById(id).orElse(null);

		if (buscaCategoria != null) {
			dao.delete(buscaCategoria);
			
			SuccessResponse<String> success = SuccessResponse.<String>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.CREATED.value())
			        .success(HttpStatus.CREATED.getReasonPhrase())
			        .response("Categoria eliminado correctamente")
			        .build();
			
			return ResponseEntity.status(HttpStatus.OK).body(success);
		} else {
			throw new RuntimeException("Problema al buscar el producto");
		}
	}

}
