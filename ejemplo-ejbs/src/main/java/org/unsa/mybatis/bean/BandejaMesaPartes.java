package org.unsa.mybatis.bean;

public class BandejaMesaPartes {
	
	private Integer id;
	private String estado;
	private String detalle;
	private Integer requerimientoId;
	private String asunto;
	private Integer usuarioId;
	private String nombre;
	private String dni;
	
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
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

}
