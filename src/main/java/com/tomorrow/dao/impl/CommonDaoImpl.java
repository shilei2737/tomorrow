package com.tomorrow.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.tomorrow.dao.CommonDao;
import com.tomorrow.dao.SimpleMapper;

@Component("commonDao")
public class CommonDaoImpl implements CommonDao{
	@Resource
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSessionProxy;
	private final static Object syncLock = new Object();    
	

	@Override
	public SimpleMapper mapper(Class<?> mapperClass) {
		return new SimpleMapperImpl(this).setMapperClass(mapperClass, SimpleMapperImpl.EXPANSION_BLANK);
	}


	SqlSessionFactory getSqlSessionFactory() {
		return this.sqlSessionFactory;
	}

	SqlSession getSqlSessionProxy() {
		if(null == sqlSessionProxy){
			synchronized (syncLock) {
				if(null == sqlSessionProxy){
					this.sqlSessionProxy = new SqlSessionTemplate(sqlSessionFactory);
				}
			}
		}
		return this.sqlSessionProxy;
	}
}
