package com.springboot.microservices.hotelservice.service;

import java.util.List;

import com.springboot.microservices.hotelservice.entities.Hotel;

public interface HotelService {

	Hotel createHotel(Hotel hotel);

	List<Hotel> getAllHotels();

	Hotel getHotelById(String Id);

	Hotel updateHotelById(String Id, Hotel hotel);

	void deleteHotelById(String Id);

}
