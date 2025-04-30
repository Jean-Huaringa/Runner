package com.cibertec.runner.dto;

public class ProductoDTO {
	
	private Integer id;
    private Integer stock;
    private Integer idClr;
    private Integer idTll;
    private Integer idMdl;

    
    public ProductoDTO() {}

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getIdClr() {
        return idClr;
    }

    public Integer getIdTll() {
        return idTll;
    }

    public Integer getIdMdl() {
        return idMdl;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setIdClr(Integer idClr) {
        this.idClr = idClr;
    }

    public void setIdTll(Integer idTll) {
        this.idTll = idTll;
    }

    public void setIdMdl(Integer idMdl) {
        this.idMdl = idMdl;
    }

}
