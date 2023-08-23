package com.springboot.microservices.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservices.hotelservice.entities.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, String> {

}
