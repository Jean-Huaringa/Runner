package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.ProductoDTO;
import com.cibertec.runner.model.Producto;

public interface ProductoService {

	// Listar todos
	public ResponseEntity<Map<String, Object>> findAllProductos();

	/// Obtener por ID
	public ResponseEntity<Map<String, Object>> findByIdProducto(Integer id);

	/// Registrar
	public ResponseEntity<Map<String, Object>> saveProducto(ProductoDTO productoDTO);

	/// Actualizar
	public ResponseEntity<Map<String, Object>> updateProducto(Producto producto, Integer id);

	// Eliminar de manera permanente
	public ResponseEntity<Map<String, Object>> deleteByIdProducto(Integer id);

}
