package com.leandro.simpleEverythingAPI.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leandro.simpleEverythingAPI.models.User;

public interface IUserRepo extends MongoRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
    User deleteByUsername(String username);
	User insert(User user);
	User save(User user);
}
