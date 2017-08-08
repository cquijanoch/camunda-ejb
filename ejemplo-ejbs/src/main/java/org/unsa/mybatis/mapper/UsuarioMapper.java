package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.Usuario;

public interface UsuarioMapper {
	
	public void saveUsuario(Usuario usuario);
	public List<Usuario> getAll();
	public Usuario getUserByDni(String dni);
}
