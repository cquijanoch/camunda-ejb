package org.unsa.mybatis.bean;

public class BandejaDiga {
	
	private Integer id;
	private String estadoMesaPartes;
	private String detalle;
	private Integer requerimientoId;
	private String asunto;
	private Integer usuarioId;
	private String nombre;
	private String dni;
	private String estadoDiga;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEstadoMesaPartes() {
		return estadoMesaPartes;
	}

	public void setEstadoMesaPartes(String estadoMesaPartes) {
		this.estadoMesaPartes = estadoMesaPartes;
	}

	public String getEstadoDiga() {
		return estadoDiga;
	}

	public void setEstadoDiga(String estadoDiga) {
		this.estadoDiga = estadoDiga;
	}
	
}
