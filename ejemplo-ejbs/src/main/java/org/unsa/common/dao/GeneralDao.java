package org.unsa.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.unsa.mybatis.config.MyBatisSqlSessionFactory;

public class GeneralDao {

	public SqlSession getSession()
	{
		return MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
	}
	
}
