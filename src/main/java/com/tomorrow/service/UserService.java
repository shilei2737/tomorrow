package com.tomorrow.service;

import java.util.List;

import com.tomorrow.entity.User;

public interface UserService {
	public List<User> getAllUser();

	public User getUserByName(String userName);

	public User getUser(User user);

	public void addUser(User user);

	public void addUsers(List<User> user);

	public void updateUser(User user);

	public void deleteUser(int UserId);
}
