package com.tomorrow.dao;

import java.util.List;

import com.tomorrow.entity.User;

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
