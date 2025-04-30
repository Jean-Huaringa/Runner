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

import com.cibertec.runner.model.Material;
import com.cibertec.runner.repository.IMaterialRepository;
import com.cibertec.runner.service.MaterialService;

@Service
public class MaterialServiceImp implements MaterialService{

	@Autowired
	private IMaterialRepository dao;

	@Override
	public ResponseEntity<Map<String, Object>> listMateriales() {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA LISTADO DE MATERIALES
		List<Material> materiales = dao.findAll();
		
		// VERIFICAR SI LA LISTA ESTA VACIA
		if(!materiales.isEmpty()) {
			// MENSAJE CON ENVIO DE LISTADO
			respuesta.put("mensaje", "Listado de materiales");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("materiales", materiales);
			
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
	public ResponseEntity<Map<String, Object>> listMaterialById(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA MATERIAL BUSCADO POR ID
		Optional<Material> material = dao.findById(id);
		
		// VERIFICA SI EL MATERIAL EXISTE
		if(material.isPresent()) {
			// MENSAJE CON EL MATERIAL ENCONTRADO
			respuesta.put("mensaje", "Material encontrado");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("material", material.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			// MENSAJE SI NO ENCUENTRA MATERIAL
			respuesta.put("mensaje", "No se encuentran registros con el ID: " + id);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> createMaterial(Material m) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
		try {
			// REALIZA REGISTRO
			dao.save(m);
			
			// MENSAJE DE EXITO EN LA CREACION
			respuesta.put("mensaje", "Material creado");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("material", m);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch (Exception e) {
			// MENSAJE EN CASO DE ERROR EN LA CREACION
			respuesta.put("mensaje", "Error al crear material");
			respuesta.put("error", e.getMessage());
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateMaterial(Material m, Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA MATERIAL BUSCADO POR ID
		Optional<Material> buscaMaterial = dao.findById(id);
		
		// VERIFICA SI EL MATERIAL YA EXISTE EN LA BD
		if(!buscaMaterial.isPresent()) {
			// MENSAJE SI NO ENCUENTRA MATERIAL
			respuesta.put("mensaje", "El material con el ID especifico no existe");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
		try {
			// ACTUALIZA VALORES DE EL MATERIAL A ACTUALIZAR
			Material materialActual = buscaMaterial.get();
			materialActual.setNombre(m.getNombre());
			
			// CAPTURA MATERIAL ACTUALIZADO
			Material materialActualizado = dao.save(materialActual);
			
			// MENSAJE DE EXITO EN LA ACTUALIZACION
			respuesta.put("mensaje", "Material actualizado con exito");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("material", materialActualizado);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch(Exception e) {
			// MENSAJE EN CASO DE ERROR
			respuesta.put("mensaje", "Error al actualizar material");
			respuesta.put("error", e.getMessage());
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> deleteMaterial(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		// CAPTURA MATERIAL BUSCADO POR ID
		Optional<Material> buscaMaterial = dao.findById(id);
		
		// VERIFICA SI EL MATERIAL YA EXISTE
		if(buscaMaterial.isPresent()) {
			dao.delete(buscaMaterial.get());
			
			// RESPUESTA EN CASO DE EXITO EN LA ELIMINACION
			respuesta.put("mensaje", "Material eliminado con exito");
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
