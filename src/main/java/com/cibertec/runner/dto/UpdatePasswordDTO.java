package com.cibertec.runner.dto;
import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private String contraseniaActual;
    private String nuevaContrasenia;
}
