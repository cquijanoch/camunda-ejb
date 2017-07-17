package org.unsa.mybatis.config;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlSessionFactory {
	protected static final SqlSessionFactory FACTORY;
	 
    static {
        try {
            Reader reader = Resources.getResourceAsReader("org/unsa/mybatis/xml/Configuration.xml");
            FACTORY = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e){
            throw new RuntimeException("Fatal Error.  Cause: " + e, e);
        }
    }
 
    public static SqlSessionFactory getSqlSessionFactory() {
        return FACTORY;
    }
}
