package com.springboot.microservices.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservices.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
