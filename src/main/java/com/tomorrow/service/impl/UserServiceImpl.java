/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: UserServiceImpl.java
 * Author:   Administrator
 * Date:     2015年9月25日 下午5:06:05
 * Description: 
 */
package com.tomorrow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.User;
import com.tomorrow.service.UserService;

/**
 * 〈一句话是什么〉<br> 
 * 〈详细描述做什么〉
 *
 * @author Administrator
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;

	@Override
	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int UserId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void addUsers(List<User> list) {
		userDao.addUsers(list);
	}

}
