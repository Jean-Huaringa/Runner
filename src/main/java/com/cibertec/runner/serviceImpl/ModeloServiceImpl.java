package com.cibertec.runner.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.model.Modelo;
import com.cibertec.runner.model.ModeloDTO;
import com.cibertec.runner.repository.IModeloRepository;
import com.cibertec.runner.service.ModeloService;

@Service
public class ModeloServiceImpl implements ModeloService{
	
	@Autowired
	private IModeloRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listModelos(){
		Map<String, Object> respuesta = new HashMap<>();
		List<Modelo> modelos = dao.findAll();
		
		if (!modelos.isEmpty()) {
			respuesta.put("mensaje", "Lista de Modelos");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("Modelos", modelos);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen Registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> agregaModelo(ModeloDTO modeloDTO){
		Map<String, Object> respuesta = new LinkedHashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String fechaActual = LocalDateTime.now().format(formatter);
		
			Modelo modelo = new Modelo();

	        modelo.setDescripcion(modeloDTO.getDescripcion());
	        modelo.setInfo(modeloDTO.getInfo());
	        modelo.setEstado(modeloDTO.getEstado());
	        modelo.setPrecio(modeloDTO.getPrecio());
	        modelo.setIdCtg(modeloDTO.getIdCtg());
	        modelo.setIdMrc(modeloDTO.getIdMrc());
	        modelo.setIdPrn(modeloDTO.getIdPrn());
	        modelo.setIdMtl(modeloDTO.getIdMtl());
	        
	        Modelo modeloGuardado = dao.save(modelo);
	        
	        respuesta.put("mensaje", "Modelo registrado con éxito");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.CREATED);
	        respuesta.put("modelo", modeloGuardado);
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> actualizaModelo(ModeloDTO modeloDTO, Long id){
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);
	    
	    Optional<Modelo> modEncontrada = dao.findById(id);
	    
	    if (modEncontrada.isPresent()) {
	        Modelo modelo = modEncontrada.get();

	        modelo.setDescripcion(modeloDTO.getDescripcion());
	        modelo.setInfo(modeloDTO.getInfo());
	        modelo.setEstado(modeloDTO.getEstado());
	        modelo.setPrecio(modeloDTO.getPrecio());
	        modelo.setIdCtg(modeloDTO.getIdCtg());
	        modelo.setIdMrc(modeloDTO.getIdMrc());
	        modelo.setIdPrn(modeloDTO.getIdPrn());
	        modelo.setIdMtl(modeloDTO.getIdMtl());

	        Modelo modeloActualizado = dao.save(modelo);

	        respuesta.put("mensaje", "Modelo actualizado con éxito");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.OK);
	        respuesta.put("modelo", modeloActualizado);

	        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "Modelo no encontrado");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.NOT_FOUND);

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> eliminarLogicoModelo(Long id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String fechaActual = LocalDateTime.now().format(formatter);
		Optional<Modelo> modEncontrado = dao.findById(id);
		
		if(modEncontrado.isPresent()) {
			Modelo mod = modEncontrado.get();
			mod.setEstado(0.0);
			dao.save(mod);
			respuesta.put("mensaje", "Mascota eliminado correctamente");
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.NO_CONTENT);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros para el ID: " + id);
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		 
	}
	
	
}
