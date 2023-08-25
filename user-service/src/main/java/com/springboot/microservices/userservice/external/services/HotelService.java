package com.springboot.microservices.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.microservices.userservice.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/{id}")
	Hotel getHotelById(@PathVariable("id") String hotelId);

}
