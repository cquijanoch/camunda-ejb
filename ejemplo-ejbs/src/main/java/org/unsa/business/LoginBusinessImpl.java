package org.unsa.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.unsa.common.dao.LoginDao;
import org.unsa.dto.RolDto;

@Stateless
public class LoginBusinessImpl implements LoginBusiness {
	
	private LoginDao loginDao;
	
	public LoginDao getService() {
		return this.loginDao;
	}
	@PostConstruct
	public void initialize() {
		this.loginDao = new LoginDao();
	}
	
	@Override
	public Map<String, Object> listarRolesByNickname(String nickname) {
		List<RolDto> roles=loginDao.getRolesByNickname(nickname);
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("roles", roles);
		return request;
	}
	
	
}
