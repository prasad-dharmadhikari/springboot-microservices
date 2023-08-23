package com.springboot.microservices.hotelservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservices.hotelservice.entities.Hotel;
import com.springboot.microservices.hotelservice.exception.ResourceNotFoundException;
import com.springboot.microservices.hotelservice.repository.HotelRepository;
import com.springboot.microservices.hotelservice.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		hotel.setId(UUID.randomUUID().toString());
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String Id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + Id));
	}

	@Override
	public Hotel updateHotelById(String Id, Hotel hotel) {
		// TODO Auto-generated method stub
//		hotelRepository.findById(Id)
//				.orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + Id));
		return null;
	}

	@Override
	public void deleteHotelById(String Id) {
		hotelRepository.findById(Id)
		.orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + Id));
		hotelRepository.deleteById(Id);
	}

}
