package org.unsa.mybatis.bean;

public class BandejaMesaPartes {
	
	private Integer id;
	private String estado;
	private String detalle;
	private Integer requerimientoId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Integer getRequerimientoId() {
		return requerimientoId;
	}
	public void setRequerimientoId(Integer requerimientoId) {
		this.requerimientoId = requerimientoId;
	}
	
	

}
