package org.unsa.common.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.unsa.mybatis.config.MyBatisSqlSessionFactory;

public class GeneralDao {

	public SqlSessionFactory getSessionFactory()
	{
		return MyBatisSqlSessionFactory.getSqlSessionFactory();
	}
	
}
