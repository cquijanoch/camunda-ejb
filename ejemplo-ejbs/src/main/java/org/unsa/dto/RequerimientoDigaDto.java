package org.unsa.dto;

public class RequerimientoDigaDto {

	private Integer requerimientoId;
	private String asunto;
	private UsuarioDto usuarioDto;
	private boolean aprobado;

	public boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
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

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

}
