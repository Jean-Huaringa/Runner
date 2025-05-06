package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.TicketDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Ticket;

public interface TicketService {

	public ResponseEntity<SuccessResponse<List<Ticket>>> findAllTickets();

	public ResponseEntity<SuccessResponse<Ticket>> findByIdTicket(Integer id);

	public ResponseEntity<SuccessResponse<Ticket>> saveTicket(TicketDTO ticketDTO);

	public ResponseEntity<SuccessResponse<Ticket>> updateTicket(TicketDTO ticketDTO, Integer id);

	public ResponseEntity<SuccessResponse<String>> deleteTicket(Integer id);

}
