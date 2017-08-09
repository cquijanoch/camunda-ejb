package org.unsa.business;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.unsa.common.dao.MesaPartesDao;
import org.unsa.common.dao.UsuarioDao;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.RequerimientoDto;
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
		RequerimientoDto requerimientoDto=(RequerimientoDto) request.get("usuarioRequerimiento");
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setDni(requerimientoDto.getDni());
		usuarioDto.setNombre(requerimientoDto.getNombre());
		
		UsuarioDto usuarioSearched=usuarioDao.searchUserByDni(usuarioDto.getDni());
		
		ReqMesaPartesDto registro = null;
		
		if(usuarioSearched==null)
		{
			usuarioDao.saveUser(usuarioDto);
			requerimientoDto.setId(usuarioDto.getId());
			registro=usuarioDao.saveRequerimiento(requerimientoDto);
			usuarioDao.saveReqMesaPartes(registro);
			
		} else {
			usuarioDto.setId(usuarioSearched.getId());
			requerimientoDto.setId(usuarioDto.getId());
			registro=usuarioDao.saveRequerimiento(requerimientoDto);
			usuarioDao.saveReqMesaPartes(registro);
		}
		
		request.put("ReqMesaPartesDto", registro);
		
	
		return request;
	}
	

}
