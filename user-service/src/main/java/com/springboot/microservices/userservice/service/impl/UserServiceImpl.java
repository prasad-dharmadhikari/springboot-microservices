package com.springboot.microservices.userservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservices.userservice.entities.User;
import com.springboot.microservices.userservice.exceptions.ResourceNotFoundException;
import com.springboot.microservices.userservice.repository.UserRepository;
import com.springboot.microservices.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByID(String userId) {
		return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with given id: "+userId));
	}

	@Override
	public void deleteUserByID(String userId) {

	}

	@Override
	public User updateUser(String userId, User user) {
		return null;
	}

}
