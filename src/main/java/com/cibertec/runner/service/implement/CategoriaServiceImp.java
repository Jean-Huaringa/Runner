package com.cibertec.runner.service.implement;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.model.Categoria;
import com.cibertec.runner.repository.ICategoriaRepository;
import com.cibertec.runner.service.CategoriaService;

@Service
public class CategoriaServiceImp implements CategoriaService{
	
	@Autowired
	private ICategoriaRepository dao;

	@Override
	public ResponseEntity<Map<String, Object>> findAllListCategoria() {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA LISTADO DE CATEGORIAS
		List<Categoria> categorias = dao.findAll();
		
		// VERIFICAR SI LA LISTA ESTA VACIA
		if(!categorias.isEmpty()) {
			// MENSAJE CON ENVIO DE LISTADO
			respuesta.put("mensaje", "Listado de categorias");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("categorias", categorias);
			
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			// MENSAJE SI LA LISTA ESTA VACIA
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> findByIdCategoria(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA CATEGORIA BUSCADA POR ID
		Optional<Categoria> categoria = dao.findById(id);
		
		// VERIFICA SI LA CATEGORIA EXISTE
		if(categoria.isPresent()) {
			// MENSAJE CON LA CATEGORIA ENCONTRADA
			respuesta.put("mensaje", "Categoria encontrada");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("categoria", categoria.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			// MENSAJE SI NO ENCUENTRA CATEGORIA
			respuesta.put("mensaje", "No se encuentran registros con el ID: " + id);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> saveCategoria(Categoria c) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
		try {
			// REALIZA REGISTRO
			dao.save(c);
			
			// MENSAJE DE EXITO EN LA CREACION
			respuesta.put("mensaje", "Categoria creada");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("categoria", c);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch (Exception e) {
			// MENSAJE  EN CASO DE ERROR EN LA CREACION
			respuesta.put("mensaje", "Error al crear categoria");
			respuesta.put("error", e.getMessage());
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateCategoria(Categoria c, Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA LA CATEGORIA BUSCADA POR SU ID
		Optional<Categoria> buscaCategoria = dao.findById(id);
		
		// VERIFICA SI LA CATEGORIA YA EXISTE EN LA BD
		if(!buscaCategoria.isPresent()) {
			// MENSAJE EN CASO LA CATEGORIA NO EXISTE
			respuesta.put("mensaje", "La categoria con el ID especificado no existe");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
		try {
			// ACTUALIZA VALORES DE LA CATEGORIA A ACTUALIZAR
			Categoria categoriaActual = buscaCategoria.get();
			categoriaActual.setNombre(c.getNombre());
			
			// CAPTURA CATEGORIA ACTUALIZADA
			Categoria categoriaActualizada = dao.save(categoriaActual);
			
			// MENSAJE DE EXITO EN LA ACTUALIZACION
			respuesta.put("mensaje", "Categoria actualizada con exito");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("categoria", categoriaActualizada);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch (Exception e) {
			// MENSAJE EN CASO DE ERROR
			respuesta.put("mensaje", "Error al actualizar categoria");
			respuesta.put("error", e.getMessage());
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> deleteByIdCategoria(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA LA CATEGORIA BUSCADA POR SU ID
		Optional<Categoria> buscaCategoria = dao.findById(id);
		
		// VERIFICA SI LA CATEGORIA EXISTE
		if(buscaCategoria.isPresent()) {
			// ELIMINA LA CATEGORIA
			dao.delete(buscaCategoria.get());
			
			// RESPUESTA EN CASO DE EXITO EN LA ELIMINACION
			respuesta.put("mensaje", "Categoria eliminada con exito");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			// MENSAJE EN CASO DE ERROR EN LA ELIMINACION
			respuesta.put("mensaje", "Error al eliminar, no se encuentran registros con el ID: " + id);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
