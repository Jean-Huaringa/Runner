package com.cibertec.runner.dto;

import lombok.Data;

@Data
public class ModeloDTO {
	
	private Integer id;
	private String descripcion;
	private String info;
	private Boolean estado;
	private Double precio;
	private Integer idCtg;
	private Integer idMrc;
	private Integer idPrn;
	private Integer idMtl;
}
