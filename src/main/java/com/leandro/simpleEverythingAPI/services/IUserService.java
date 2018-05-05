package com.leandro.simpleEverythingAPI.services;

import java.util.List;

import com.leandro.simpleEverythingAPI.models.User;

public interface IUserService {
	public List<User> findAll();
	public User findByUsername(String userName);
	public User createUser(User user);
	public Object updateUser(User user);
	public User deleteUser(String userName);
}
