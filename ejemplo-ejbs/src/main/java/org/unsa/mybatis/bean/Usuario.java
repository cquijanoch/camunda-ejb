package org.unsa.mybatis.bean;

public class Usuario {
	
	private Integer usuarioId;
	private String  nombre;
	private Integer dni;
	private String dnistring;
	
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
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getDnistring() {
		return dnistring;
	}

	public void setDnistring(String dnistring) {
		this.dnistring = dnistring;
	}
	
	
}
