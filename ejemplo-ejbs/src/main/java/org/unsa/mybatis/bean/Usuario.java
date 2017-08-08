package org.unsa.mybatis.bean;

public class Usuario {
	
	private Integer usuarioId;
	private String  nombre;
	private String dni;
	
	public Usuario(){
		
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
