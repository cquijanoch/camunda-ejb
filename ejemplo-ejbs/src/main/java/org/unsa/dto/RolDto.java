package org.unsa.dto;

import java.io.Serializable;

public class RolDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int rolId;
	private String nombre;
	
	public RolDto() {
		// TODO Auto-generated constructor stub
	}

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
