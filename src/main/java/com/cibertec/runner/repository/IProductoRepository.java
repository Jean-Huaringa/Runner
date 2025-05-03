package com.cibertec.runner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByIdMdl(Integer idMdl);
}
