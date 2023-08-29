package com.springboot.microservices.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservices.userservice.entities.User;
import com.springboot.microservices.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	int retryCount = 1;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	//@CircuitBreaker(name = "ratingHotelBraker", fallbackMethod = "ratingHotelFallback")
	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
		logger.info("Retry Count : {}",retryCount);
		retryCount++;
		return new ResponseEntity<>(userService.getUserByID(userId),HttpStatus.OK);
	}
	
	//create ratingFallback method for circuit braker
	
	public ResponseEntity<User> ratingHotelFallback(String id, Exception ex) {
		//logger.info("Fall back is executed because service is down: ",ex.getMessage());
		User user = User.builder()
			.email("dummy@gmail.com")
			.name("Dummy Name")
			.about("This is user is created dummy because some service is down")
			.userId(id)
			.build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping
	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	

}
