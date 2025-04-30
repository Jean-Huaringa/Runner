package com.cibertec.runner.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;

public interface ITransaccionService {
		
	List<Transaccion> listarTodas();
	
    //Optional<Transaccion> buscarTransaccion(Integer idPrd, Integer idTck);
    
    Transaccion registrar(Transaccion transaccion);
    
    //Transaccion actualizar(Transaccion transaccion);
    
    public  ResponseEntity<Map<String, Object>> eliminarTransaccion(TransaccionId id);
	
}
