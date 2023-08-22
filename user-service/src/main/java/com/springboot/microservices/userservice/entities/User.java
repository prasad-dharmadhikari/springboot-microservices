package com.springboot.microservices.userservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "ID")
	private String userId;

	@Column(name = "NAME", length = 15)
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ABOUT")
	private String about;

}
