package com.cibertec.runner.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.model.Ticket;
import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;
import com.cibertec.runner.repository.ITransacionRepository;
import com.cibertec.runner.service.ITransaccionService;

import jakarta.transaction.Transactional;

@Service
public class TransaccionServiceImpl implements ITransaccionService {

    @Autowired
    private ITransacionRepository iTransaccionRepository;

   	@Override
	public List<Transaccion> listarTodas() {
		return iTransaccionRepository.findAll();
	}

	@Override
	@Transactional
	public Transaccion registrar(Transaccion transaccion) {
		  return iTransaccionRepository.save(transaccion);
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarTransaccion(TransaccionId id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
		 
		 Optional<Transaccion> transaccionExiste = iTransaccionRepository.findById(id);
		 
		 if (transaccionExiste.isPresent()) {
			 iTransaccionRepository.delete(transaccionExiste.get());
		        respuesta.put("mensaje", "Transaccion eliminada con éxito");
		        respuesta.put("status", HttpStatus.OK);

		        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		    } else {
		        respuesta.put("mensaje", "No se realizo la Eliminacion, Transaccion no encontrada");
		        respuesta.put("status", HttpStatus.NOT_FOUND);

		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		    }
		
	}
	
	
}
