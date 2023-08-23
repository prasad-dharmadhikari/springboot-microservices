package com.springboot.microservices.ratingservice.service;

import java.util.List;

import com.springboot.microservices.ratingservice.entities.Rating;

public interface RatingService {

	Rating createRating(Rating rating);

	List<Rating> getRatings();
	
	Rating getRatingById(String ratingId);

	List<Rating> getRatingsByUserId(String userId);

	List<Rating> getRatingsByHotelId(String hotelId);

}
