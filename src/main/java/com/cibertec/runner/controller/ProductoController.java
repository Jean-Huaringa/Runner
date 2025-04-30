package com.cibertec.runner.controller;

import java.util.Map;
import java.util.List;
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

import com.cibertec.runner.dto.ProductoDTO;
import com.cibertec.runner.model.Producto;
import com.cibertec.runner.serviceImpl.ProductoServiceImpl;


@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	
	@Autowired
	private ProductoServiceImpl psimpl;
	
	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> listProducto(){
		return psimpl.listarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Integer id) {
	    return psimpl.obtenerPorId(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> Actualizar(@RequestBody Producto producto, @PathVariable Integer id){
		return psimpl.actualizar(producto, id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> Agregar(@RequestBody ProductoDTO productoDTO){
		return psimpl.registrar(productoDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
	    return psimpl.eliminarProducto(id);
	}

	
	
}
