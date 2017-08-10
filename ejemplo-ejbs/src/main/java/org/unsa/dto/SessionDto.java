package org.unsa.dto;

import java.io.Serializable;

public class SessionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserDto usuario;
	private String jwt;
	private String url;
	private Integer rolId;
	
	public SessionDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDto usuario) {
		this.usuario = usuario;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	
}
