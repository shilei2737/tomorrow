/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: UserDaoImpl.java
 * Author:   Administrator
 * Date:     2015年9月25日 下午4:29:17
 * Description: 
 */
package com.tomorrow.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import com.tomorrow.dao.UserDao;
import com.tomorrow.entity.User;

/**
 * 〈一句话是什么〉<br> 
 * 〈详细描述做什么〉
 *
 * @author Administrator
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(value="userDao")
public class UserDaoImpl implements UserDao{
	
	@Resource
	private SqlSessionFactory sqlSessionFactory;
	/* (non-Javadoc)
	 * @see com.dynamo.dao.UserDao#getUser(com.dynamo.entity.User)
	 */
	@Override
	public User getUser(User user) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dynamo.dao.UserDao#addUser(com.dynamo.entity.User)
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.dynamo.dao.UserDao#updateUser(com.dynamo.entity.User)
	 */
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.dynamo.dao.UserDao#deleteUser(int)
	 */
	@Override
	public void deleteUser(int UserId) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.dynamo.dao.UserDao#getUserByName(java.lang.String)
	 */
	@Override
	public User getUserByName(String userName) {
		SqlSession session = sqlSessionFactory.openSession();
		User user = (User) session.selectOne("com.tomorrow.entity.User.getUserByName",userName);
		session.close();
		return user;
	}

	/* (non-Javadoc)
	 * @see com.dynamo.dao.UserDao#getAllUser()
	 */
	@Override
	public List<User> getAllUser() {
		SqlSession session = sqlSessionFactory.openSession();
		List<User> userList = session.selectList("com.tomorrow.entity.User.getAllUser");
		session.close();
		return userList;
	}

	/* (non-Javadoc)
	 * @see com.dynamo.dao.UserDao#addUsers(java.util.List)
	 */
	@Override
	public void addUsers(List<User> list) {
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("com.tomorrow.entity.User.insertList", list);
		session.close();
	}

	/* (non-Javadoc)
	 * @see com.dynamo.dao.UserDao#getAllUsersName()
	 */
	@Override
	public List<String> getAllUsersName() {
		// TODO Auto-generated method stub
		return null;
	}

}
