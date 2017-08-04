package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.Usuario;

public interface UsuarioMapper {
	
	void insert(Usuario usuario);
	List<Usuario> getAll();
	void insertTest(Usuario usuario);

}
