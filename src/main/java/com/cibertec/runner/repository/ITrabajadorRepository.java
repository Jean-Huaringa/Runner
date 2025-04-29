package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Trabajador;

@Repository
public interface ITrabajadorRepository extends JpaRepository<Trabajador, Integer>{
}
