
package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Ticket;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
}
