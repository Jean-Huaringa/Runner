package com.cibertec.runner.dto;

import lombok.Data;

@Data
public class AuthDTO {
    private String token;
    private String mail;
    private String contrasenia;
}
