package com.springboot.microservices.userservice.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.microservices.userservice.entities.Hotel;
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

		List<User> users = userRepository.findAll();
		return users.stream().map(user -> {
			user.setRatings(getRatingsAndHotelsByUserId(user.getUserId()));
			return user;
		}).collect(Collectors.toList());
	}

	@Override
	public User getUserByID(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with given id: " + userId));
		// fetch rating from rating-service and hotels associated from hotel-service

		user.setRatings(getRatingsAndHotelsByUserId(userId));

		return user;
	}

	@Override
	public void deleteUserByID(String userId) {

	}

	@Override
	public User updateUser(String userId, User user) {
		return null;
	}

	private List<Rating> getRatingsAndHotelsByUserId(String userId) {

		String ratingServiceUrl = "http://RATING-SERVICE/ratings/users?userId=" + userId;
		String hotelServiceUrl = "http://HOTEL-SERVICE/hotels/";
		Rating[] ratingsOfUser = restTemplate.getForObject(ratingServiceUrl, Rating[].class);
		// List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList = Arrays.stream(ratingsOfUser).toList().stream().map(rating -> {
			Hotel hotel = restTemplate.getForEntity(hotelServiceUrl + rating.getHotelId(), Hotel.class).getBody();
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());

		return ratingList;
	}
}
