package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Distrito;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Integer> {

	boolean existsByNombre(String nombre);
}
