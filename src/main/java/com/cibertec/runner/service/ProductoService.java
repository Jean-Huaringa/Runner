package com.cibertec.runner.service;
import com.cibertec.runner.dto.ProductoDTO;
import com.cibertec.runner.model.Producto;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Producto;

public interface ProductoService {
	
	 //Listados
	    /////// Todos
	    public ResponseEntity<Map<String, Object>> listarTodos();
	    /// Obtener por ID
	    public ResponseEntity<Map<String, Object>> obtenerPorId(Integer id);
	    
	    /// Registrar
	    public ResponseEntity<Map<String, Object>> registrar(ProductoDTO productoDTO);

	    /// Actualizar
	   public ResponseEntity< Map<String, Object>> actualizar(Producto producto, Integer id);

	   //Eliminar de manera permanente
	   public  ResponseEntity<Map<String, Object>> eliminarProducto(Integer id);
	
}




 
    
