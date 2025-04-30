package com.cibertec.runner.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.TicketDTO;
import com.cibertec.runner.model.Ticket;
import com.cibertec.runner.model.Usuario;
import com.cibertec.runner.repository.ITicketRepository;
import com.cibertec.runner.repository.IUsuarioRepository;
import com.cibertec.runner.service.TicketService;

import jakarta.transaction.Transactional;


@Service
public class TicketServiceImp implements TicketService {

	@Autowired
	private ITicketRepository tkRepo;
	
	@Autowired
	private IUsuarioRepository usuRepo;
	
	
	
	
	
	@Override
	public ResponseEntity<Map<String, Object>> listarTodos() {
		
		Map<String, Object> respuesta = new LinkedHashMap<>();
		
			List<Ticket> tickets = tkRepo.findAll();
		
		
		
		if (!tickets.isEmpty()) {
			respuesta.put("mensaje", "Lista de Tickets");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("Tickets", tickets);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}


	
	}
	
	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> registrar(TicketDTO ticketDTO) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);
	    Usuario usuEncontrado = usuRepo.findById(ticketDTO.getIdUsr())
	            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
		  
	    Ticket ticket = new Ticket();
	    ticket.setIdUsr(usuEncontrado.getId());
	    ticket.setDireccion(ticketDTO.getDireccion());
	 


	    tkRepo.save(ticket);

	    respuesta.put("mensaje", "Se ha agregado correctamente el Ticket");
	    respuesta.put("fecha", fechaActual);
	    respuesta.put("status", HttpStatus.CREATED);
	    respuesta.put("Ticket", ticket);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

	}
	

	@Override
	public ResponseEntity<Map<String, Object>> obtenerPorId(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		Optional<Ticket> ticket = tkRepo.findById(id);

	    if (ticket.isPresent()) {
	        respuesta.put("mensaje", "Ticket Encontrado");
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.OK);
	        respuesta.put("ticket", ticket.get());
	        return ResponseEntity.ok().body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No se encuentra un registro para el ID: " + id);
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }

	}






	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> actualizar(TicketDTO ticketDTO, Integer id) {
		
		Map<String, Object> respuesta = new LinkedHashMap<>();
	    
	    Optional<Ticket> tickEncontrado = tkRepo.findById(id);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);

	    Usuario usuEncontrado = usuRepo.findById(ticketDTO.getIdUsr())
	            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
	    
		if(tickEncontrado.isPresent()) {
			
			Ticket ticket = tickEncontrado.get();
			ticket.setIdUsr(usuEncontrado.getId());
		    ticket.setDireccion(ticketDTO.getDireccion());
		

			tkRepo.save(ticket);
			
			respuesta.put("mensaje", "Ticket modificado correctamente");
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("ticket", ticket);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros para el ID: " + id);
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.NOT_FOUND);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}

	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarTicket(Integer id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 String fechaActual = LocalDateTime.now().format(formatter);
		 
		 Optional<Ticket> ticketExiste = tkRepo.findById(id);
		
		if (ticketExiste.isPresent()) {
	        tkRepo.delete(ticketExiste.get());
	        respuesta.put("mensaje", "Ticket eliminado con éxito");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.OK);

	        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No se realizo la Eliminacion, Ticket no encontrado");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.NOT_FOUND);

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }

	}

	
	


}
