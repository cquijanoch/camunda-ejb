package org.unsa.mybatis.bean;

public class Requerimiento {
	private String asunto;
	private String usuarioId;
	private String usuarioNombre;
	private String usuarioDni;
	
	public Requerimiento(){}
	
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
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
