package com.cibertec.runner.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_transaccion")
public class Transaccion {
	
    @EmbeddedId
    private TransaccionId id;

	@Column(name = "unidades", nullable = false)
	private Integer unidades;
	
	@Column(name = "monto_total", nullable = false, length = 10)
    private Double montoTotal;

	@ManyToOne
	@JoinColumn(name = "id_prd", referencedColumnName = "id_prd", insertable = false, updatable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "id_tck", referencedColumnName = "id_tck", insertable = false, updatable = false)
	private Ticket ticket;
}
