package com.tomorrow.dao;

public interface SimpleMapper {
	/**
	 * 指定 mapper.xml 映射文件中的 Statement 语句对象
	 * 
	 * @param sql
	 * @return
	 */
	SimpleMapper sql(String sql);

	/**
	 * 创建主从分离的路由 SqlSession 代理对象
	 * 
	 * @return
	 */
	SimpleSqlSession session();
}
