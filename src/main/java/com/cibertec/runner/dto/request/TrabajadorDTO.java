package com.cibertec.runner.dto.request;

import com.cibertec.runner.model.Usuario;

import lombok.Data;

@Data
public class TrabajadorDTO {
	
	private Double salario;
	
	private int horasLaborales;
  
    private Usuario usuario;
}
