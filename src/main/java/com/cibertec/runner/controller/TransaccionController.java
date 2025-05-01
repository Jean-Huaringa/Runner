package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;
import com.cibertec.runner.service.TransaccionService;

@RestController
@RequestMapping("/api/transaccion")
public class TransaccionController {
	
	@Autowired
    private TransaccionService transaccionService;

    // Listar todas
    @GetMapping
    public ResponseEntity<List<Transaccion>> findAllListTransaccion() {
        return ResponseEntity.ok(transaccionService.findAllListTransaccion());
    }

    // Registrar
    @PostMapping
    public ResponseEntity<Transaccion> saveTransaccion(@RequestBody Transaccion transaccion) {
        return ResponseEntity.ok(transaccionService.saveTransaccion(transaccion));
    }
    
    @DeleteMapping("/deleteTr")
    public ResponseEntity<SuccessResponse<String>> deleteByIdTransaccion(@RequestBody TransaccionId id) {
    	System.out.println("Entrando DeleteMapping");
        return transaccionService.deleteByIdTransaccion(id);
    }
}
