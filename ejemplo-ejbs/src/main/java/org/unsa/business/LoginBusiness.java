package org.unsa.business;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.unsa.dto.UsuarioDto;

@Local
public interface LoginBusiness {
	
	public Map<String,Object> listarRolesByNickname(String nickname);
	public Map<String,Object> identityUser(String nickname,String password);
	public boolean isValidAuthentication(String tokenRequest);

	
}
