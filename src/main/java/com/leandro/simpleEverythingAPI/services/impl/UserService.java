package com.leandro.simpleEverythingAPI.services.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.simpleEverythingAPI.models.User;
import com.leandro.simpleEverythingAPI.repositories.IUserRepo;
import com.leandro.simpleEverythingAPI.services.IUserService;

@Service
public class UserService implements IUserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private IUserRepo userRepo;

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	@Override
	public User createUser(User user) {
		return userRepo.insert(user);
	}

	@Override
	public Object updateUser(User user) {
		User toUpd = userRepo.findByUsername(user.getUsername());
		if (toUpd != null) {
			return userRepo.save(user);
		}
		return new Exception("User not found");
	}

	@Override
	public User deleteUser(String userName) {
		return userRepo.deleteByUsername(userName);
	}

	@Override
	public User findByUsername(String userName) {
		return userRepo.findByUsername(userName);
	}
}
