package org.unsa.business;

import java.util.Map;

import javax.ejb.Local;
import org.unsa.dto.UsuarioDto;

@Local
public interface UsuarioBusiness {
	
	public Map<String,Object> saveRequerimiento(Map<String,Object> request);

}
