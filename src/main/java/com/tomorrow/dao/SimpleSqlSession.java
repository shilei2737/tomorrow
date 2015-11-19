package com.tomorrow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

public interface SimpleSqlSession {
	
	<T> T selectOne();

	<T> T selectOne(Object parameter);

	<E> List<E> selectList();

	<E> List<E> selectList(Object parameter);

	<E> List<E> selectList(Object parameter, RowBounds rowBounds);

	<K, V> Map<K, V> selectMap(String mapKey);

	<K, V> Map<K, V> selectMap(Object parameter, String mapKey);

	<K, V> Map<K, V> selectMap(Object parameter, String mapKey,
			RowBounds rowBounds);

	void select(Object parameter, ResultHandler handler);

	void select(ResultHandler handler);

	void select(Object parameter, RowBounds rowBounds, ResultHandler handler);

	int insert();

	int insert(Object parameter);

	int update();

	int update(Object parameter);

	int delete();

	int delete(Object parameter);
}
