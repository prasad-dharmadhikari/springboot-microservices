package com.springboot.microservices.ratingservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.microservices.ratingservice.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

	List<Rating> findByRatingId(String ratingId);

	List<Rating> findByUserId(String userId);

	List<Rating> findByHotelId(String hotelId);

}
