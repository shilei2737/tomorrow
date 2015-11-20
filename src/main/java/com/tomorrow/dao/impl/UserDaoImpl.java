package com.tomorrow.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tomorrow.dao.CommonDao;
import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.User;

@Component(value="userDao")
public class UserDaoImpl implements UserDao{
	
	@Resource
	private CommonDao commonDao;

	@Override
	public User getUser(User user) {
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
	public User getUserByName(String userName) {
		return commonDao.mapper(User.class).sql("getUserByName").session().selectOne(userName);
	}

	@Override
	public List<User> getAllUser() {
		return commonDao.mapper(User.class).sql("getAllUser").session().selectList();
	}

	@Override
	public void addUsers(List<User> list) {
		commonDao.mapper(User.class).sql("insertList").session().insert(list);
	}

	@Override
	public List<String> getAllUsersName() {
		// TODO Auto-generated method stub
		return null;
	}

}
