/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: SimpleMapperImpl.java
 * Author:   Administrator
 * Date:     2015年11月19日 下午4:26:45
 * Description: 
 */
package com.tomorrow.dao.impl;

import org.apache.ibatis.session.SqlSession;

import com.tomorrow.dao.SimpleMapper;
import com.tomorrow.dao.SimpleSqlSession;
import org.apache.commons.lang3.StringUtils;

public class SimpleMapperImpl implements SimpleMapper {
	static final String EXPANSION_BLANK = ".";
	private Class<?> mapperClass;
	private String sql;
	private CommonDaoImpl commonDaoImpl;
	private String expansion = EXPANSION_BLANK;

	SimpleMapperImpl(CommonDaoImpl commonDaoImpl) {
		this.commonDaoImpl = commonDaoImpl;
	}

	SimpleMapper setMapperClass(Class<?> mapperClass, String expansion) {
		this.mapperClass = mapperClass;
		if (null != expansion && !expansion.isEmpty() )
			this.expansion = expansion;
		return this;
	}

	@Override
	public SimpleMapper sql(String sql) {
		this.sql = sql;
		return this;
	}

	@Override
	public SimpleSqlSession session() {
		this.argumentCheck();
		return new SimpleSqlSessionImpl(this.wrapperSqlSession(), this.buildStatement());
	}


	private void argumentCheck() {
		if (this.mapperClass == null)
			throw new IllegalArgumentException("请设置Mapper名称空间对应的实体");
		if (StringUtils.isBlank(this.sql))
			throw new IllegalArgumentException("请设置要执行的SQL语句");
	}

	private String buildStatement() {
		return StringUtils.join(this.mapperClass.getName(), this.expansion, this.sql);
	}

	private SqlSession wrapperSqlSession() {
		SqlSession sqlSession = commonDaoImpl.getSqlSessionProxy();
		return sqlSession;
	}
}