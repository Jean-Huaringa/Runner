package com.cibertec.runner.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;

public interface ITransaccionService {
		
	List<Transaccion> listarTodas();
    
    Transaccion registrar(Transaccion transaccion);
    
    public  ResponseEntity<Map<String, Object>> eliminarTransaccion(TransaccionId id);
	
}
