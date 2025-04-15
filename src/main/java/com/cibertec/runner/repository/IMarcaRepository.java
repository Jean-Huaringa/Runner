package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Marca;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Integer>{
}

