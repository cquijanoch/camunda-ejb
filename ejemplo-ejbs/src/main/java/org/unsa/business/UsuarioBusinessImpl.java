package org.unsa.business;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.unsa.common.dao.MesaPartesDao;
import org.unsa.common.dao.UsuarioDao;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.UsuarioDto;

@Stateless
public class UsuarioBusinessImpl implements UsuarioBusiness {

	private UsuarioDao usuarioDao;
	
	@PostConstruct
	public void initialize() {
		this.usuarioDao = new UsuarioDao();
		
	}
	

	@Override
	public Map<String,Object> saveRequerimiento(Map<String,Object> request) {
		UsuarioDto usuarioDto=(UsuarioDto) request.get("usuarioRequerimiento");
		UsuarioDto usuarioSearched=usuarioDao.searchUserByDni(usuarioDto.getDni());
		if(usuarioSearched==null)
		{
			usuarioDao.saveUser(usuarioDto);
			ReqMesaPartesDto registro=usuarioDao.saveRequerimiento(usuarioDto);
			usuarioDao.saveReqMesaPartes(registro);
			
		} else {
			usuarioDto.setId(usuarioSearched.getId());
			ReqMesaPartesDto registro=usuarioDao.saveRequerimiento(usuarioDto);
			usuarioDao.saveReqMesaPartes(registro);
		}
	
		return request;
	}
	

}
