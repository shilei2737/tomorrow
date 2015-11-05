/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: UserService.java
 * Author:   Administrator
 * Date:     2015年9月25日 下午5:05:34
 * Description: 
 */
package com.tomorrow.service;

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
public interface UserService {
	public List<User> getAllUser();

	public User getUserByName(String userName);

	public User getUser(User user);

	public void addUser(User user);

	public void addUsers(List<User> user);

	public void updateUser(User user);

	public void deleteUser(int UserId);
}
