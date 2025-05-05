package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
	boolean existsByNombre(String nombre);
}
