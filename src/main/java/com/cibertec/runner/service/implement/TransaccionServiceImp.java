package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;
import com.cibertec.runner.repository.ITransacionRepository;
import com.cibertec.runner.service.TransaccionService;

import jakarta.transaction.Transactional;

@Service
public class TransaccionServiceImp implements TransaccionService {

    @Autowired
    private ITransacionRepository iTransaccionRepository;

   	@Override
	public List<Transaccion> findAllListTransaccion() {
		return iTransaccionRepository.findAll();
	}

	@Override
	@Transactional
	public Transaccion saveTransaccion(Transaccion transaccion) {
		  return iTransaccionRepository.save(transaccion);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteByIdTransaccion(TransaccionId id) {
	    Optional<Transaccion> transaccionExiste = iTransaccionRepository.findById(id);

	    if (transaccionExiste.isPresent()) {
	        iTransaccionRepository.delete(transaccionExiste.get());

	        SuccessResponse<String> success = SuccessResponse.<String>builder()
	                .timestamp(LocalDateTime.now())
	                .status(HttpStatus.NO_CONTENT.value())
	                .success(HttpStatus.NO_CONTENT.getReasonPhrase())
	                .response("Transacción eliminada con éxito")
	                .build();

	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success);
	    } else {
	        throw new RuntimeException("No se realizó la eliminación, transacción no encontrada");
	    }
	}
	
	
}
