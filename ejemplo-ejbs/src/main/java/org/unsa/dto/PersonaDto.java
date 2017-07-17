package org.unsa.dto;

import java.io.Serializable;

public class PersonaDto implements Serializable{
	private static final long serialVersionUID = -4648674837948322592L;
	
	private String nombre;
	private String segundoNombre;
	private String apellidos;
	private String segundoApellido;
	
	public PersonaDto()
	{
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
