package org.unsa.business;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.unsa.camunda.CamundaApi;
import org.unsa.common.dao.EjemploDao;
import org.unsa.common.dao.UsuarioDao;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.UsuarioDto;

@Stateless
public class UsuarioBusinessImpl implements UsuarioBusiness {
	
	@EJB
	private CamundaApi camundaApi;
	
	private UsuarioDao usuarioDao;
	
	public UsuarioDao getService() {
		return this.usuarioDao;
	}
	@PostConstruct
	public void initialize() {
		this.usuarioDao = new UsuarioDao();
	}
	

	@Override
	public ProcessDto saveUser(ProcessDto processDto) {
		UsuarioDto usuarioDto = processDto.getUsuarioDto();
		this.usuarioDao.saveUser(usuarioDto);
		camundaApi.createProcess(processDto);
		return processDto;
	}
	@Override
	public UsuarioDto saveUser(UsuarioDto usuarioDto) {
		this.usuarioDao.saveUser(usuarioDto);
		return usuarioDto;
	}

}
