package com.cibertec.runner.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.TicketDTO;

public interface TicketService {
	
    /// Listados
    /////// Todos
    public ResponseEntity<Map<String, Object>> listarTodos();
    /// Obtener por ID
    public ResponseEntity<Map<String, Object>> obtenerPorId(Integer id);
    
    /// Registrar
    public ResponseEntity<Map<String, Object>> registrar(TicketDTO ticketDTO);

    /// Actualizar
   public ResponseEntity< Map<String, Object>> actualizar(TicketDTO ticketDTO, Integer id);

   
   //Eliminar de manera permanente
   public  ResponseEntity<Map<String, Object>> eliminarTicket(Integer id);




}
