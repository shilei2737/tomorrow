/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: CommonDao.java
 * Author:   Administrator
 * Date:     2015年11月19日 上午10:26:47
 * Description: 
 */
package com.tomorrow.dao;

/**
 * 〈一句话是什么〉<br> 
 * 〈详细描述做什么〉
 *
 * @author Administrator
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface CommonDao {
	/**
	 * 创建Mapper对象，设置 myBatis 的 Mapper 对应的实体类
	 * 
	 * @param mapperClass mapper中namespace对应的实体类
	 * @return
	 */
	SimpleMapper mapper(Class<?> mapperClass);

}
