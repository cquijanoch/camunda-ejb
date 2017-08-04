package org.unsa.common.dao;

import org.unsa.dto.RequerimientoDto;
import org.unsa.dto.UsuarioDto;
import org.unsa.mybatis.bean.Requerimiento;
import org.unsa.mybatis.mapper.RequerimientoMapper;

public class RequerimientoDao extends GeneralDao {
	
	
	public RequerimientoDto saveRequerimiento(RequerimientoDto requerimiento){
		RequerimientoMapper mapper = getSession().getMapper(RequerimientoMapper.class);
		Requerimiento requerimientoBean = new Requerimiento();
		
		UsuarioDto usuarioDto = requerimiento.getUsuarioDto();
		
		requerimientoBean.setAsunto(requerimiento.getAsunto());
		requerimientoBean.setUsuarioId(usuarioDto.getId());

		try
		{
			mapper.save(requerimientoBean);
			getSession().commit();
		}

		finally
		{
			getSession().close();
		}
		return requerimiento ;
	}
}

