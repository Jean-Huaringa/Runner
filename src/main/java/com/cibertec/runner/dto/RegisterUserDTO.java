package com.cibertec.runner.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {
	private String nombre;
	private String apellido;
	private String nmrDocumento;
	private String telefono;
	private String mail;
	private String contrasenia;
}


