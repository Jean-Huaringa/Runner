package com.cibertec.runner.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;
import com.cibertec.runner.service.ITransaccionService;

@RestController
@RequestMapping("/api/transaccion")
public class TransaccionController {
	
	@Autowired
    private ITransaccionService transaccionService;

    // Listar todas
    @GetMapping
    public ResponseEntity<List<Transaccion>> listarTransacciones() {
    	System.out.println("Entrando Listar");
        return ResponseEntity.ok(transaccionService.listarTodas());
    }

    // Buscar una
    @GetMapping("/{idPrd}/{idTck}")
    public ResponseEntity<Transaccion> obtenerTransaccion(@PathVariable Integer idPrd, @PathVariable Integer idTck) {
        return transaccionService.buscarTransaccion(idPrd, idTck)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registrar
    @PostMapping
    public ResponseEntity<Transaccion> registrar(@RequestBody Transaccion transaccion) {
        return ResponseEntity.ok(transaccionService.registrar(transaccion));
    }

    // Actualizar
    @PutMapping("/{idPrd}/{idTck}")
    public ResponseEntity<Transaccion> actualizar(@RequestBody Transaccion transaccion) {
        return ResponseEntity.ok(transaccionService.actualizar(transaccion));
    }

    // Eliminar
    /*@DeleteMapping("/{idPrd}/{idTck}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idPrd, @PathVariable Integer idTck) {
        transaccionService.eliminar(idPrd, idTck);
        return ResponseEntity.noContent().build();
    }*/
    
    @DeleteMapping("/deleteTr")
    public ResponseEntity<Map<String, Object>> eliminar(@RequestBody TransaccionId id) {
    	System.out.println("Entrando DeleteMapping");
        return transaccionService.eliminarTransaccion(id);
    }
}
