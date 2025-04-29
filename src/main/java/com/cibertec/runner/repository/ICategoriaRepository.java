package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{
}
