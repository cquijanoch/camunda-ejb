package org.unsa.business;



import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.unsa.common.dao.RequerimientoDao;
import org.unsa.common.dao.UsuarioDao;
import org.unsa.dto.RequerimientoDto;
import org.unsa.dto.UsuarioDto;

@Stateless
public class RequerimientoBusinessImpl implements RequerimientoBusiness {
	
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
	public RequerimientoDto save(RequerimientoDto requerimiento) {
		
		UsuarioDto usuarioDto = requerimiento.getUsuarioDto();
		
//		this.usuarioDao.saveUser(usuarioDto);
//		this.requerimientoDao.saveRequerimiento(requerimiento);
//		
		return requerimiento;
	}

}
