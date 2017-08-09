package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.BandejaDiga;
import org.unsa.mybatis.bean.BandejaMesaPartes;
import org.unsa.mybatis.bean.BandejaMesaPartesRequerimiento;
import org.unsa.mybatis.bean.Rol;

public interface LoginMapper {

	List<Rol> getRolesByNickName(String nickname);

}
