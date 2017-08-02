package org.unsa.business;

import javax.ejb.EJB;
import javax.ejb.Local;

import org.unsa.camunda.CamundaApi;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.UsuarioDto;

@Local
public interface UsuarioBusiness {
	
	public UsuarioDto saveUser(UsuarioDto usuarioDto);
	public ProcessDto saveUser(ProcessDto processDto);
	
}
