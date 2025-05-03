package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.ProductoDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Producto;

public interface ProductoService {

	// Listar todos
	public ResponseEntity<SuccessResponse<List<Producto>>> findAllProductos();

	/// Obtener por ID
	public ResponseEntity<SuccessResponse<Producto>> findByIdProducto(Integer id);

	/// Registrar
	public ResponseEntity<SuccessResponse<Producto>> saveProducto(ProductoDTO productoDTO);

	/// Actualizar
	public ResponseEntity<SuccessResponse<Producto>> updateProducto(Producto producto, Integer id);

	// Eliminar de manera permanente
	public ResponseEntity<SuccessResponse<String>> deleteByIdProducto(Integer id);

	public ResponseEntity<SuccessResponse<List<Producto>>> findByIdMdl(Integer id);
}
