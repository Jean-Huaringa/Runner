package com.cibertec.runner.controller;

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

import com.cibertec.runner.dto.request.FiltroProductoDTO;
import com.cibertec.runner.dto.request.ProductoDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Producto;
import com.cibertec.runner.service.implement.ProductoServiceImp;


@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	
	@Autowired
	private ProductoServiceImp psimpl;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Producto>>> findAllProductos(){
		return psimpl.findAllProductos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Producto>> findByIdProducto(@PathVariable Integer id) {
	    return psimpl.findByIdProducto(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Producto>> updateProducto(@RequestBody Producto producto, @PathVariable Integer id){
		return psimpl.updateProducto(producto, id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Producto>> saveProducto(@RequestBody ProductoDTO productoDTO){
		return psimpl.saveProducto(productoDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdProducto(@PathVariable Integer id) {
	    return psimpl.deleteByIdProducto(id);
	}
	
	@GetMapping("/modelo/{idMdl}")
	public ResponseEntity<SuccessResponse<List<Producto>>> findByIdMdl(@PathVariable Integer idMdl) {
	    return psimpl.findByIdMdl(idMdl);
	}
	
	@PostMapping("/filtros")
	public ResponseEntity<SuccessResponse<List<Producto>>> findByAttributes(@RequestBody FiltroProductoDTO idMdl) {
	    return psimpl.findByAttributes(idMdl);
	}

}
