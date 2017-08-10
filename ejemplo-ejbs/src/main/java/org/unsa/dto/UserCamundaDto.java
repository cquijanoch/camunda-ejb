package org.unsa.dto;

import java.util.List;

public class UserCamundaDto {
	private String idUsuario;
	private String password;
	private List<GroupCamundaDto> Grupos;

	public List<GroupCamundaDto> getGrupos() {
		return Grupos;
	}

	public void setGrupos(List<GroupCamundaDto> grupos) {
		Grupos = grupos;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
