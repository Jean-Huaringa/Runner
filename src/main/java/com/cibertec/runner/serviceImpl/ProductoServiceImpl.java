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

import com.cibertec.runner.dto.ProductoDTO;
import com.cibertec.runner.model.Producto;
import com.cibertec.runner.repository.IProductoRepository;
import com.cibertec.runner.service.ProductoService;

@Service

public class ProductoServiceImpl implements ProductoService  {
 
	@Autowired
 private IProductoRepository prorepo;
	
	
	@Override 
	public ResponseEntity<Map<String, Object>> listarTodos() {
		
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
		List<Producto> product = prorepo.findAll();
	
	
	if (!product.isEmpty()) {
		respuesta.put("mensaje", "Lista de Productos");
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.OK);
		respuesta.put("Productos", product);
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	} else {
		respuesta.put("mensaje", "No existen registros");
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	}
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> obtenerPorId(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		Optional<Producto> producto = prorepo.findById(id);

	    if (producto.isPresent()) {
	        respuesta.put("mensaje", "Producto Encontrado");
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.OK);
	        respuesta.put("producto", producto.get());
	        return ResponseEntity.ok().body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No se encuentra un registro para el ID: " + id);
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> registrar(ProductoDTO productoDTO) {

	    Map<String, Object> respuesta = new LinkedHashMap<>();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);
		
	    Producto produc = new Producto();
	    
	    produc.setId(productoDTO.getId()); 
        produc.setStock(productoDTO.getStock()); 
        produc.setIdClr(productoDTO.getIdClr());
        produc.setIdTll(productoDTO.getIdTll());
        produc.setIdMdl(productoDTO.getIdMdl());
	    prorepo.save(produc);

	    respuesta.put("mensaje", "Se ha agregado correctamente el producto");
	    respuesta.put("fecha", fechaActual);
	    respuesta.put("status", HttpStatus.CREATED);
	    respuesta.put("producto", produc);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

	@Override
	public ResponseEntity<Map<String, Object>> actualizar(Producto producto, Integer id) {

	    Map<String, Object> response = new HashMap<>();
	    try {
	        Optional<Producto> productoExistente = prorepo.findById(id);

	        if (productoExistente.isPresent()) {
	            
	            Producto productoActualizado = productoExistente.get();
	      
	            productoActualizado.setStock(producto.getStock());
	            productoActualizado.setIdClr(producto.getIdClr());
	            productoActualizado.setIdTll(producto.getIdTll());
	            productoActualizado.setIdMdl(producto.getIdMdl());
	            
			   Producto productoGuardado = prorepo.save(productoActualizado);

	            response.put("mensaje", "Producto actualizado exitosamente.");
	            response.put("producto", productoGuardado);
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("mensaje", "Producto no encontrado.");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (Exception e) {
	        response.put("mensaje", "Error al actualizar el producto.");
	        response.put("error", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	

	@Override
	public ResponseEntity<Map<String, Object>> eliminarProducto(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 String fechaActual = LocalDateTime.now().format(formatter);
		 
		 Optional<Producto> productoExiste = prorepo.findById(id);
		
		if (productoExiste.isPresent()) {
	        prorepo.delete(productoExiste.get());

	        respuesta.put("mensaje", "Producto eliminado con éxito");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.OK);

	        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No se realizo la Eliminacion, Producto no encontrado");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.NOT_FOUND);

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	  }
	}

	
	

	

	
	
	
	
	
	


