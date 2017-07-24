package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.Usuario;

public interface UsuarioMapper {
	
	int save(Usuario usuario);
	List<Usuario> getAll();
}
