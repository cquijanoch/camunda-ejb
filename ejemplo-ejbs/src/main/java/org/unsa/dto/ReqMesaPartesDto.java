package org.unsa.dto;

public class ReqMesaPartesDto {
	private Integer id;
    private String estado;
    private String estadoDiga;
    private Integer requerimientoId;
    private String detalle;
    private String asunto;
    private String nombre;
    private String dni;
    private Integer usuarioId;
 
    public ReqMesaPartesDto(){
    	
    }

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

	public Integer getRequerimientoId() {
		return requerimientoId;
	}

	public void setRequerimientoId(Integer requerimientoId) {
		this.requerimientoId = requerimientoId;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
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

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
    
	public String getEstadoDiga() {
		return estadoDiga;
	}

	public void setEstadoDiga(String estadoDiga) {
		this.estadoDiga = estadoDiga;
	}

	
	
 
}
