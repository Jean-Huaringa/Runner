package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;

public interface TransaccionService {
		
	List<Transaccion> findAllListTransaccion();
    
    Transaccion saveTransaccion(Transaccion transaccion);
    
    public  ResponseEntity<SuccessResponse<String>> deleteByIdTransaccion(TransaccionId id);
	
}
