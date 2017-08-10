package org.unsa.dto;

public class ExpedienteDigaDto {
	private String dni;
	private String nombre;
	private String detalle;
	private boolean cheque;
	private boolean deposito;
	private boolean efectivo;
	
	
	public ExpedienteDigaDto()
	{
		
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public void setCheque(boolean cheque) {
		this.cheque = cheque;
	}
	
	public void setDeposito(boolean deposito) {
		this.deposito = deposito;
	}
	
	public void setEfectivo(boolean efectivo) {
		this.efectivo = efectivo;
	}
	
	
	
	public boolean getCheque() {
		return cheque;
	}
	
	public boolean getDeposito() {
		return deposito;
	}
	
	public boolean getEfectivo() {
		return efectivo;
	}
	
	
}

