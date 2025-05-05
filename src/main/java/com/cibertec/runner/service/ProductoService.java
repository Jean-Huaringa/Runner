package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.FiltroProductoDTO;
import com.cibertec.runner.dto.request.ProductoDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Producto;

public interface ProductoService {

	public ResponseEntity<SuccessResponse<List<Producto>>> findAllProductos();

	public ResponseEntity<SuccessResponse<Producto>> findByIdProducto(Integer id);

	public ResponseEntity<SuccessResponse<Producto>> saveProducto(ProductoDTO productoDTO);

	public ResponseEntity<SuccessResponse<Producto>> updateProducto(Producto producto, Integer id);

	public ResponseEntity<SuccessResponse<String>> deleteByIdProducto(Integer id);

	public ResponseEntity<SuccessResponse<List<Producto>>> findByIdMdl(Integer id);
	
	public ResponseEntity<SuccessResponse<List<Producto>>> findByAttributes(FiltroProductoDTO filtro);
}
