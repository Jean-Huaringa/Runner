package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.TicketDTO;

public interface TicketService {
	
    /// Listados Todos
    public ResponseEntity<Map<String, Object>> findAllTickets();
    /// Obtener por ID
    public ResponseEntity<Map<String, Object>> findByIdTicket(Integer id);
    
    /// Registrar
    public ResponseEntity<Map<String, Object>> saveTicket(TicketDTO ticketDTO);

    /// Actualizar
   public ResponseEntity< Map<String, Object>> updateTicket(TicketDTO ticketDTO, Integer id);

   //Eliminar de manera permanente
   public  ResponseEntity<Map<String, Object>> deleteTicket(Integer id);




}
