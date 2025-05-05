package com.cibertec.runner.dto.response;

import java.util.List;

import com.cibertec.runner.model.Categoria;
import com.cibertec.runner.model.Marca;
import com.cibertec.runner.model.Material;
import com.cibertec.runner.model.Persona;
import com.cibertec.runner.model.Producto;

import lombok.Data;

@Data
public class ModeloProductoResponse {
	private Integer id;
	private String descripcion;
	private String info;
	private Boolean estado;
	private Double precio;
	private Integer idCtg;
	private Integer idMrc;
	private Integer idPrn;
	private Integer idMtl;
    private Categoria categoria;
    private Marca marca;
    private Persona persona;
    private Material material;
    private List<Producto> producto;
}
