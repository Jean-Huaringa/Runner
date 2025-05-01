package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.TicketDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Ticket;

public interface TicketService {
	
    /// Listados Todos
    public ResponseEntity<SuccessResponse<List<Ticket>>> findAllTickets();
    /// Obtener por ID
    public ResponseEntity<SuccessResponse<Ticket>> findByIdTicket(Integer id);
    
    /// Registrar
    public ResponseEntity<SuccessResponse<Ticket>> saveTicket(TicketDTO ticketDTO);

    /// Actualizar
   public ResponseEntity< SuccessResponse<Ticket>> updateTicket(TicketDTO ticketDTO, Integer id);

   //Eliminar de manera permanente
   public  ResponseEntity<SuccessResponse<String>> deleteTicket(Integer id);




}
