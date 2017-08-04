package org.unsa.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.unsa.camunda.CamundaApi;
import org.unsa.common.dao.EjemploDao;
import org.unsa.common.dao.RequerimientoDao;
import org.unsa.common.dao.UsuarioDao;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UsuarioDto;

@Stateless
public class UsuarioBusinessImpl implements UsuarioBusiness {
	
	@EJB
	private CamundaApi camundaApi;
	
	private UsuarioDao usuarioDao;
	
	private RequerimientoDao requerimientoDao;
	
	public UsuarioDao getService() {
		return this.usuarioDao;
	}
	@PostConstruct
	public void initialize() {
		this.usuarioDao = new UsuarioDao();
		this.requerimientoDao = new RequerimientoDao();
	}
	

	@Override
	public ProcessDto saveUser(ProcessDto processDto) {
		UsuarioDto usuarioDto = processDto.getUsuarioDto();
		this.usuarioDao.saveUser(usuarioDto);
		
		camundaApi.createProcess(processDto);
		
		List<TaskDto> activeTasks = camundaApi.getTaskByProcessInstance(processDto);
		
		if(activeTasks.size() == 1){
			
			TaskDto userTask = activeTasks.get(0);
			camundaApi.completeTask(userTask);
		}
		
		return processDto;
	}
	@Override
	public UsuarioDto saveUser(UsuarioDto usuarioDto) {
		this.usuarioDao.saveUser(usuarioDto);
		return usuarioDto;
	}

}
