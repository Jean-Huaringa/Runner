package com.cibertec.runner.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "tb_ticket")
public class Ticket {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tck")
	private Integer id;
	
	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;
	
	@Column(name = "fecha_creacion")
    @CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	@Column(name = "id_usr", nullable = false)
	private Integer idUsr;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_usr", referencedColumnName = "id_usr", insertable = false, updatable = false)
    private Usuario usuario;
	
}
