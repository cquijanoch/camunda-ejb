package org.unsa.mybatis.bean;

public class BandejaMesaPartesRequerimiento {
	
	private Integer id;
	private String estado;
	private String detalle;
	private Integer requerimientoId;
	private Integer requerimientoAsunto;
	private Integer usuarioId;
	private String usuarioNombre;
	private String usuarioDni;
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
	public Integer getRequerimientoAsunto() {
		return requerimientoAsunto;
	}
	public void setRequerimientoAsunto(Integer requerimientoAsunto) {
		this.requerimientoAsunto = requerimientoAsunto;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsuarioNombre() {
		return usuarioNombre;
	}
	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}
	public String getUsuarioDni() {
		return usuarioDni;
	}
	public void setUsuarioDni(String usuarioDni) {
		this.usuarioDni = usuarioDni;
	}
	
	

}
