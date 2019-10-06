package com.subrat.rest.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrat.rest.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
