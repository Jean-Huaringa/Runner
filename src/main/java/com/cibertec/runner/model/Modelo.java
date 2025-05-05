package com.cibertec.runner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_modelo")
public class Modelo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mdl", nullable = false)
	private Integer id;
	
	@Column(name = "descripcion", nullable = false, length = 100)
	private String descripcion;
	
	@Column(name = "informacion", nullable = false, length = 255)
	private String info;
	
	@Column(name = "estado", nullable = false)
	private Boolean estado;
	
	@Column(name = "precio")
	private Double precio;

	@Column(name = "id_ctg", nullable = false)
	private Integer idCtg;

	@Column(name = "id_mrc", nullable = false)
	private Integer idMrc;

	@Column(name = "id_prn", nullable = false)
	private Integer idPrn;

	@Column(name = "id_mtl", nullable = false)
	private Integer idMtl;
	
    @ManyToOne
	@JoinColumn(name = "id_ctg", referencedColumnName = "id_ctg", insertable = false, updatable = false)
    private Categoria categoria;
    
	@ManyToOne
	@JoinColumn(name = "id_mrc", referencedColumnName = "id_mrc", insertable = false, updatable = false)
    private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "id_prn", referencedColumnName = "id_prn", insertable = false, updatable = false)
    private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "id_mtl", referencedColumnName = "id_mtl", insertable = false, updatable = false)
    private Material material;
	
}
