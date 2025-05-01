package com.cibertec.runner.controller;

import java.util.List;

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

import com.cibertec.runner.dto.request.TicketDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Ticket;
import com.cibertec.runner.service.implement.TicketServiceImp;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

	@Autowired
	private TicketServiceImp tks;

	@GetMapping("/listado")
	public ResponseEntity<SuccessResponse<List<Ticket>>> findAllTickets() {
		return tks.findAllTickets();
	}

	// Obtener por ID
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Ticket>> findByIdTicket(@PathVariable Integer id) {
		return tks.findByIdTicket(id);
	}

	// Registrar
	@PostMapping("/registrar")
	public ResponseEntity<SuccessResponse<Ticket>> saveTicket(@RequestBody TicketDTO ticketDTO) {
		return tks.saveTicket(ticketDTO);
	}

	// Actualizar
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Ticket>> updateTicket(@RequestBody TicketDTO ticketDTO, @PathVariable Integer id) {
		return tks.updateTicket(ticketDTO, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteTicket(@PathVariable Integer id) {
		return tks.deleteTicket(id);
	}

}
