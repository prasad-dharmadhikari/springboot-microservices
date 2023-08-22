package com.springboot.microservices.userservice.service;

import java.util.List;

import com.springboot.microservices.userservice.entities.User;

public interface UserService {

	User saveUser(User user);

	List<User> getAllUsers();

	User getUserByID(String userId);

	void deleteUserByID(String userId);

	User updateUser(String userId, User user);

}
