package org.unsa.common.dao;

import java.util.List;

import org.unsa.mybatis.bean.Contoh;
import org.unsa.mybatis.mapper.ContohMapper;

public class EjemploDao extends GeneralDao{

	
	
	public List<Contoh> getAllContoh()
	{
		ContohMapper mapper = getSession().getMapper(ContohMapper.class);
        return  mapper.selectAll();
        
	}
}
