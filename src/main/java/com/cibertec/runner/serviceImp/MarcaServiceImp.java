package com.cibertec.runner.serviceImp;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.model.Marca;
import com.cibertec.runner.repository.IMarcaRepository;
import com.cibertec.runner.service.MarcaService;

@Service
public class MarcaServiceImp implements MarcaService{
	
	@Autowired
	private IMarcaRepository dao;

	@Override
	public ResponseEntity<Map<String, Object>> listMarcas() {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA LISTADO DE MARCAS
		List<Marca> marcas = dao.findAll();
		
		// VERIFICAR SI LA LISTA ESTA VACIA
		if(!marcas.isEmpty()) {
			// MENSAJE CON ENVIO DE LISTADO
			respuesta.put("mensaje", "Listado de marcas");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("marcas", marcas);
			
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
	public ResponseEntity<Map<String, Object>> listMarcaById(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA LA MARCA BUSCADA POR ID
		Optional<Marca> marca = dao.findById(id);
		
		// VERIFICA SI LA MARCA EXISTE
		if(marca.isPresent()) {
			// MENSAJE CON LA MARCA ENCONTRADA
			respuesta.put("mensaje", "Marca encontrada");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("categoria", marca.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			// MENSAJE SI NO ENCUENTRA MARCA
			respuesta.put("mensaje", "No se encuentran registros con el ID: " + id);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> createMarca(Marca m) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
		try {
			// REALIZA REGISTRO
			dao.save(m);
			
			// MENSAJE DE EXITO EN EL CREACION
			respuesta.put("mensaje", "Marca creada");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("marca", m);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch (Exception e) {
			// MENSAJE EN CASO DE ERROR EN LA CREACION
			respuesta.put("mensaje", "Error al crear marca");
			respuesta.put("error", e.getMessage());
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);			
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateMarca(Marca m, Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA LA MARCA BUSCADA POR SU ID
		Optional<Marca> buscaMarca = dao.findById(id);
		
		// VERIFICA SI LA CATEGORIA YA EXISTE EN LA BD
		if(!buscaMarca.isPresent()) {
			// MENSAJE EN CASO LA MARCA NO EXISTE
			respuesta.put("mensaje", "La marca con el ID especificado no existe");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
		try {
			// ACTUALIZA VALORES DE LA CATEGORIA A ACTUALIZAR
			Marca marcaActual = buscaMarca.get();
			marcaActual.setNombre(m.getNombre());
			
			// CAPTURA MARCA ACTUALIZADA
			Marca marcaActualizada = dao.save(marcaActual);
			
			// MENSAJE DE EXITO EN LA ACTUALIZACION
			respuesta.put("mensaje", "Marca actualizada con exito");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("marca", marcaActualizada);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch (Exception e) {
			// MENSAJE EN CASO DE ERROR
			respuesta.put("mensaje", "Error al actualizar marca");
			respuesta.put("error", e.getMessage());
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> deleteMarca(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA LA MARCA BUSCADA POR SU ID
		Optional<Marca> buscaMarca = dao.findById(id);
		
		// VERIFICA SI LA CATEGORIA EXISTE
		if (buscaMarca.isPresent()) {
			// ELIMINA LA MARCA
			dao.delete(buscaMarca.get());
			
			// MENSAJE EN CASO DE EXITO EN LA ELIMINACION
			respuesta.put("mensaje", "Marca eliminada con exito");
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
