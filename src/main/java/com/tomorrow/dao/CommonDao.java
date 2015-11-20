package com.tomorrow.dao;

public interface CommonDao {
	/**
	 * 创建Mapper对象，设置 myBatis 的 Mapper 对应的实体类
	 * 
	 * @param mapperClass mapper中namespace对应的实体类
	 * @return
	 */
	SimpleMapper mapper(Class<?> mapperClass);

}
