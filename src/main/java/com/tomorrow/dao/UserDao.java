/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: UserDao.java
 * Author:   Administrator
 * Date:     2015年9月25日 下午4:05:52
 * Description: 
 */
package com.tomorrow.dao;

import java.util.List;

import com.tomorrow.entity.User;

/**
 * 〈一句话是什么〉<br>
 * 〈详细描述做什么〉
 *
 * @author Administrator
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface UserDao {
	public List<User> getAllUser();
	
	public List<String> getAllUsersName();
	
	public User getUserByName(String userName);
	
	public User getUser(User user);

	public void addUser(User user);
	
	public void addUsers(List<User> list);

	public void updateUser(User user);

	public void deleteUser(int UserId);
}
