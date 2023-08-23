package com.springboot.microservices.ratingservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservices.ratingservice.entities.Rating;
import com.springboot.microservices.ratingservice.exception.ResourceNotFoundException;
import com.springboot.microservices.ratingservice.repository.RatingRepository;
import com.springboot.microservices.ratingservice.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public Rating getRatingById(String ratingId) {
		// TODO Auto-generated method stub
		return ratingRepository.findById(ratingId)
				.orElseThrow(() -> new ResourceNotFoundException("Rating not found with given id: " + ratingId));
	}

}
