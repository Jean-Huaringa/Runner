package com.cibertec.runner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.runner.model.Distrito;
import com.cibertec.runner.repository.IDistritoRepository;

@Service
public class DistritoService {
	
	@Autowired
	private IDistritoRepository repository;
	
	public Distrito findById(int id) {
		return repository.findById(id).orElse(null);
	}
}
