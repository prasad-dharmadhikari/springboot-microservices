package com.springboot.microservices.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservices.ratingservice.entities.Rating;
import com.springboot.microservices.ratingservice.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings() {
		return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRatingsById(@PathVariable("id") String ratingId) {
		return new ResponseEntity<>(ratingService.getRatingById(ratingId), HttpStatus.OK);
	}

	@GetMapping("/hotels")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@RequestParam("hotelId") String hotelId) {
		return new ResponseEntity<>(ratingService.getRatingsByHotelId(hotelId), HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@RequestParam("userId") String userId) {
		return new ResponseEntity<>(ratingService.getRatingsByUserId(userId), HttpStatus.OK);
	}
}
