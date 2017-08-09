package org.unsa.common.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.RequerimientoDto;
import org.unsa.dto.RolDto;
import org.unsa.dto.UserDto;
import org.unsa.dto.UsuarioDto;
import org.unsa.mybatis.bean.BandejaMesaPartes;
import org.unsa.mybatis.bean.Requerimiento;
import org.unsa.mybatis.bean.Rol;
import org.unsa.mybatis.bean.User;
import org.unsa.mybatis.bean.Usuario;
import org.unsa.mybatis.mapper.LoginMapper;
import org.unsa.mybatis.mapper.RequerimientoMapper;
import org.unsa.mybatis.mapper.UsuarioMapper;
import org.unsa.util.Constantes;

public class LoginDao extends GeneralDao {
	private static final Logger LOGGER = Logger.getLogger(LoginDao.class.getName());

	public List<RolDto> getRolesByNickname(String nickname)
	{
		List<RolDto> response=new ArrayList<RolDto>();
		SqlSession sqlSession=getSessionFactory().openSession();
		try{
			LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
			List<Rol> list=new ArrayList<Rol>();
			list=  mapper.getRolesByNickName(nickname);
			
			for(Rol req:list)
			{
				RolDto obj=new RolDto();
				obj.setNombre(req.getNombre());
				obj.setRolId(req.getRolId());
				response.add(obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return response;
		
	}
	
	public UserDto identityUser(String nickname,String password)
	{
		UserDto response=new UserDto();
		SqlSession sqlSession=getSessionFactory().openSession();
		try{
			LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
			User obj=new User();
			HashMap<String, Object> values = new HashMap<String, Object>();
		    values.put("nickname", nickname);
		    values.put("password", password);
			obj=  mapper.getUserByNickAndPass(values);
			
			response.setId(obj.getId());
			response.setNombre(obj.getNickname());
			response.setPassword(obj.getPassword());
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return response;
		
	}
	
	
	
}
