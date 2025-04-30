package com.cibertec.runner.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;

public interface TransaccionService {
		
	List<Transaccion> findAllListTransaccion();
    
    Transaccion saveTransaccion(Transaccion transaccion);
    
    public  ResponseEntity<Map<String, Object>> deleteByIdTransaccion(TransaccionId id);
	
}
