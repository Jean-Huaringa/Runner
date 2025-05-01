package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.request.ProductoDTO;
import com.cibertec.runner.model.Producto;
import com.cibertec.runner.service.implement.ProductoServiceImp;


@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	
	@Autowired
	private ProductoServiceImp psimpl;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> findAllProductos(){
		return psimpl.findAllProductos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> findByIdProducto(@PathVariable Integer id) {
	    return psimpl.findByIdProducto(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateProducto(@RequestBody Producto producto, @PathVariable Integer id){
		return psimpl.updateProducto(producto, id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveProducto(@RequestBody ProductoDTO productoDTO){
		return psimpl.saveProducto(productoDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteByIdProducto(@PathVariable Integer id) {
	    return psimpl.deleteByIdProducto(id);
	}

	
	
}
