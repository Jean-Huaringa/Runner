package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Categoria;
import com.cibertec.runner.repository.ICategoriaRepository;
import com.cibertec.runner.service.CategoriaService;

import jakarta.persistence.NoResultException;

@Service
public class CategoriaServiceImp implements CategoriaService {

	@Autowired
	private ICategoriaRepository repository;

	@Override
	public ResponseEntity<SuccessResponse<List<Categoria>>> findAllListCategoria() {
		
		List<Categoria> categorias = repository.findAll();

		if (categorias.isEmpty()) {
			throw new RuntimeException("No se encontro ninguna categoria");
		}
		
		SuccessResponse<List<Categoria>> success = SuccessResponse.<List<Categoria>>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.OK.value())
				.success(HttpStatus.OK.getReasonPhrase())
				.response(categorias)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<Categoria>> findByIdCategoria(Integer id) {

		Categoria categoria = repository.findById(id).orElse(null);

		if (categoria == null) {
			throw new NoResultException("No se encontro el codigo de la categoria");
		}

		SuccessResponse<Categoria> success = SuccessResponse.<Categoria>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.OK.value())
				.success(HttpStatus.OK.getReasonPhrase())
				.response(categoria).build();

		return ResponseEntity.status(HttpStatus.OK).body(success);
		
	}

	@Override
	public ResponseEntity<SuccessResponse<Categoria>> saveCategoria(Categoria c) {

		if (repository.existsByNombre(c.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Categoria categoria = new Categoria();
		categoria.setNombre(c.getNombre());

		Categoria cate = repository.save(categoria);
		SuccessResponse<Categoria> success = SuccessResponse.<Categoria>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.CREATED.value())
				.success(HttpStatus.CREATED.getReasonPhrase())
				.response(cate)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(success);

	}

	@Override
	public ResponseEntity<SuccessResponse<Categoria>> updateCategoria(Categoria c, Integer id) {

		if (repository.existsByNombre(c.getNombre())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Categoria categoria = repository.findById(id).orElse(null);
		
		if (categoria == null) {
			throw new NoResultException("No se encontro el codigo de la categoria");
		}

		categoria.setNombre(c.getNombre());

		Categoria update = repository.save(categoria);

		SuccessResponse<Categoria> success = SuccessResponse.<Categoria>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.OK.value())
				.success(HttpStatus.OK.getReasonPhrase())
				.response(update)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteByIdCategoria(Integer id) {

		Categoria buscaCategoria = repository.findById(id).orElse(null);

		if (buscaCategoria == null) {
			throw new NoResultException("No se encontro el codigo de la categoria");
		}
		
		repository.delete(buscaCategoria);

		SuccessResponse<String> success = SuccessResponse.<String>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.CREATED.value())
				.success(HttpStatus.CREATED.getReasonPhrase())
				.response("Categoria eliminado correctamente")
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

}
