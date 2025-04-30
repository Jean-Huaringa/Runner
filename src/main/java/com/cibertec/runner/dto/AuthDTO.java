package com.cibertec.runner.dto;

import lombok.Data;

@Data
public class AuthDTO {
    private String token;
    private String mail;
    private String contrasenia;
    
  
    
    
}


//✅ UsuarioDTO.java → Para listar usuarios sin exponer la clave.
//✅ RegisterRequest.java → Para recibir datos de un nuevo usuario.
//✅ LoginRequest.java → Para recibir email y clave en el login.
//✅ AuthResponse.java → Para devolver un token JWT y datos del usuario autenticado.