package org.unsa.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.unsa.mybatis.bean.BandejaDiga;
import org.unsa.mybatis.bean.BandejaMesaPartes;
import org.unsa.mybatis.bean.BandejaMesaPartesRequerimiento;
import org.unsa.mybatis.bean.Rol;
import org.unsa.mybatis.bean.User;

public interface LoginMapper {

	List<Rol> getRolesByNickName(String nickname);
	User getUserByNickAndPass(@Param("nickname") String nickname, @Param("password") String password);
}
