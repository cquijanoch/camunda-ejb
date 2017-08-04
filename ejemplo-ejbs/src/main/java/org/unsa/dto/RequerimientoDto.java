package org.unsa.dto;

public class RequerimientoDto {
	
	private Integer requerimientoId;
	private String asunto;
	private UsuarioDto usuarioDto;
	
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
