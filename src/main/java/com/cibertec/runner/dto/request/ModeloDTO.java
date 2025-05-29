package com.cibertec.runner.dto.request;

import lombok.Data;

@Data
public class ModeloDTO {
	private String descripcion;
	private String info;
	private Double precio;
	private Integer idCtg;
	private Integer idMrc;
	private Integer idPrn;
	private Integer idMtl;
}
