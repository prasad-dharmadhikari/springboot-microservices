package com.springboot.microservices.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.microservices.userservice.entities.Rating;
import com.springboot.microservices.userservice.entities.User;
import com.springboot.microservices.userservice.exceptions.ResourceNotFoundException;
import com.springboot.microservices.userservice.repository.UserRepository;
import com.springboot.microservices.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

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
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with given id: " + userId));
		// fetch rating from rating-service
		String url = "http://localhost:8083/ratings/users?userId=" + userId;
		ArrayList<Rating> ratings = restTemplate.getForObject(url, ArrayList.class);
		logger.info("{}", ratings);
		user.setRatings(ratings);
		return user;
	}

	@Override
	public void deleteUserByID(String userId) {

	}

	@Override
	public User updateUser(String userId, User user) {
		return null;
	}

}
