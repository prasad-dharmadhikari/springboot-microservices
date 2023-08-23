package com.springboot.microservices.hotelservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.microservices.hotelservice.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<>(ApiResponse.builder()
				.message(ex.getMessage())
				.success(true)
				.httpStatus(HttpStatus.NOT_FOUND)
				.build(),HttpStatus.NOT_FOUND);
	}
}
