package com.tomorrow.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.tomorrow.dao.SimpleSqlSession;

public class SimpleSqlSessionImpl implements SimpleSqlSession {

	private SqlSession sqlSession;
	private String statement;

	public SimpleSqlSessionImpl(SqlSession sqlSession, String statement) {
		this.sqlSession = sqlSession;
		this.statement = statement;
	}

	@Override
	public <T> T selectOne() {
		return sqlSession.selectOne(statement);
	}

	@Override
	public <T> T selectOne(Object parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	@Override
	public <E> List<E> selectList() {
		return sqlSession.selectList(statement);
	}

	@Override
	public <E> List<E> selectList(Object parameter) {
		return sqlSession.selectList(statement, parameter);
	}

	@Override
	public <E> List<E> selectList(Object parameter, RowBounds rowBounds) {
		return sqlSession.selectList(statement, parameter, rowBounds);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String mapKey) {
		return sqlSession.selectMap(statement, mapKey);
	}

	@Override
	public <K, V> Map<K, V> selectMap(Object parameter, String mapKey) {
		return sqlSession.selectMap(statement, parameter, mapKey);
	}

	@Override
	public <K, V> Map<K, V> selectMap(Object parameter, String mapKey,
			RowBounds rowBounds) {
		return sqlSession.selectMap(statement, parameter, mapKey, rowBounds);
	}

	@Override
	public void select(Object parameter, ResultHandler handler) {
		sqlSession.select(statement, parameter, handler);
	}

	@Override
	public void select(ResultHandler handler) {
		sqlSession.select(statement, handler);
	}

	@Override
	public void select(Object parameter, RowBounds rowBounds,
			ResultHandler handler) {
		sqlSession.select(statement, parameter, rowBounds, handler);

	}

	@Override
	public int insert() {
		return sqlSession.insert(statement);
	}

	@Override
	public int insert(Object parameter) {
		return sqlSession.insert(statement, parameter);
	}

	@Override
	public int update() {
		return sqlSession.update(statement);
	}

	@Override
	public int update(Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	@Override
	public int delete() {
		return sqlSession.delete(statement);
	}

	@Override
	public int delete(Object parameter) {
		return sqlSession.delete(statement, parameter);
	}
}
