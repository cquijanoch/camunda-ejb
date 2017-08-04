package org.unsa.common.dao;


import org.unsa.dto.UsuarioDto;
import org.unsa.mybatis.bean.Usuario;
import org.unsa.mybatis.mapper.UsuarioMapper;

public class UsuarioDao extends GeneralDao{
	
	public UsuarioDto saveUser(UsuarioDto usuarioDto){
		
		UsuarioMapper mapper =getSession().getMapper(UsuarioMapper.class);
		Usuario usuarioBean = new Usuario();
		
		usuarioBean.setDni(usuarioDto.getDni());
		usuarioBean.setNombre(usuarioDto.getNombre());
		usuarioBean.setDnistring("01234567");
		
		try{
//			List<Usuario> users= mapper.getAll();
//			mapper.insertTest(usuarioBean);
			mapper.insert(usuarioBean);
			getSession().commit();
		}
		
		finally{
			getSession().close();
		}
		return usuarioDto;
	}

}
