package org.unsa.mybatis.bean;

public class Requerimiento {
	private Integer requerimientoId;
	private String asunto;
	private Integer usuarioId;
	private String usuarioNombre;
	private String usuarioDni;
	
	
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

	public Integer getRequerimientoId() {
		return requerimientoId;
	}

	public void setRequerimientoId(Integer requerimientoId) {
		this.requerimientoId = requerimientoId;
	}
	
}
