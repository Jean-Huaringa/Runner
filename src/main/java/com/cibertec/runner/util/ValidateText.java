package com.cibertec.runner.util;

import org.springframework.stereotype.Component;

@Component
public class ValidateText {

	// Validar que no esté vacío
	public boolean isRequired(String texto) {
		return texto != null && !texto.trim().isEmpty();
	}

	// Validar longitud mínima y máxima
	public boolean hasValidLength(String texto, int min, int max) {
		int longitud = texto.trim().length();
		return longitud >= min && longitud <= max;
	}

	// Validar que solo tenga letras y espacios
	public boolean hasOnlyLettersAndSpaces(String texto) {
		return texto.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
	}

	// Validar que no contenga caracteres especiales peligrosos
	public boolean hasNoneCharacterDanger(String texto) {
		return !texto.matches(".*[<>\"'%;].*");
	}

	public boolean isValidMail(String email) {
		return email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$");
	}
	
	public boolean hasOnlyNumbers(String texto) {
	    return texto.matches("^\\d+$");
	}
}
