package com.cibertec.runner.controller;

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

import com.cibertec.runner.dto.TicketDTO;
import com.cibertec.runner.service.implement.TicketServiceImp;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

	@Autowired
	private TicketServiceImp tks;

	@GetMapping("/listado")
	public ResponseEntity<Map<String, Object>> findAllTickets() {
		return tks.findAllTickets();
	}

	// Obtener por ID
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> findByIdTicket(@PathVariable Integer id) {
		return tks.findByIdTicket(id);
	}

	// Registrar
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> saveTicket(@RequestBody TicketDTO ticketDTO) {
		return tks.saveTicket(ticketDTO);
	}

	// Actualizar
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateTicket(@RequestBody TicketDTO ticketDTO, @PathVariable Integer id) {
		return tks.updateTicket(ticketDTO, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteTicket(@PathVariable Integer id) {
		return tks.deleteTicket(id);
	}

}
