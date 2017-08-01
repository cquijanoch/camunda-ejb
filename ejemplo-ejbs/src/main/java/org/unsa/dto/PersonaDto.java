package org.unsa.dto;

import java.io.Serializable;

public class PersonaDto implements Serializable{
	private static final long serialVersionUID = -4648674837948322592L;
	
	private String perAsu;
	private String perDNI;
	private String estado;
	private String perNom;
	private int perId;
	
	
	public PersonaDto()
	{
		
	}

	public String getperAsu() {
		return perAsu;
	}

	public void setperAsu(String perAsu) {
		this.perAsu = perAsu;
	}

	public String getperDNI() {
		return perDNI;
	}

	public void setperDNI(String perDNI) {
		this.perDNI = perDNI;
	}

	public String getestado() {
		return estado;
	}

	public void setestado(String estado) {
		this.estado = estado;
	}

	public String getperNom() {
		return perNom;
	}

	public void setperNom(String perNom) {
		this.perNom = perNom;
	}
	
	public int getPerId() {
		return perId;
	}
	
	public void setPerId(int perId) {
		this.perId = perId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
