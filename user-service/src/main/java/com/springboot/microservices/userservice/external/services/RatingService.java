package com.springboot.microservices.userservice.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.microservices.userservice.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@GetMapping("/ratings/users")
	List<Rating> getRatingsByUserId(@RequestParam("userId") String userId);
}
