package com.cibertec.runner.dto.request;

import lombok.Data;

@Data
public class UpdateUserDTO {
	private String nombre;
	private String apellido;
	private String nmrDocumento;
	private String telefono;
}
