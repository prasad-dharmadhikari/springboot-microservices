package com.springboot.microservices.hotelservice.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservices.hotelservice.entities.Hotel;
import com.springboot.microservices.hotelservice.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		return new ResponseEntity<>(hotelService.createHotel(hotel),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels() {
		return new ResponseEntity<>(hotelService.getAllHotels(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable("id") String hotelId) {
		return new ResponseEntity<>(hotelService.getHotelById(hotelId),HttpStatus.OK);		
	}
}
