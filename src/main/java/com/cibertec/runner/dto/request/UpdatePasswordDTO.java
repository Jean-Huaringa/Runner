package com.cibertec.runner.dto.request;
import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private String contraseniaActual;
    private String nuevaContrasenia;
}
