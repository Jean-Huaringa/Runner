package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Modelo;

@Repository
public interface IModeloRepository extends JpaRepository<Modelo, Integer>{
}
