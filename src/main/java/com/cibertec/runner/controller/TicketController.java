package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.TicketDTO;
import com.cibertec.runner.model.Ticket;
import com.cibertec.runner.serviceImpl.TicketServiceImp;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	
	
	@Autowired
	private TicketServiceImp tks;
	
	
		@GetMapping("/listado")
		public ResponseEntity<Map<String, Object>> listTickets(){
			return tks.listarTodos();
		}


	// Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object> >obtenerPorId(@PathVariable Integer id) {
        return tks.obtenerPorId(id);
    }

    // Registrar
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody TicketDTO ticketDTO) {
        return tks.registrar(ticketDTO);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@RequestBody TicketDTO ticketDTO, @PathVariable Integer id) {
        return tks.actualizar(ticketDTO, id);
    }
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar( @PathVariable Integer id) {
        return tks.eliminarTicket(id);
    }

	
}
