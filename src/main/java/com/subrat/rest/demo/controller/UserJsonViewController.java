package com.subrat.rest.demo.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.subrat.rest.demo.entities.User;
import com.subrat.rest.demo.entities.Views;
import com.subrat.rest.demo.exceptions.UserNotFoundException;
import com.subrat.rest.demo.service.UserService;

@RestController
@RequestMapping(value = "/jsonview/users")
@Validated
public class UserJsonViewController {

	@Autowired
	private UserService userService;

	@JsonView(Views.External.class)
	@GetMapping("external/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFoundException.getMessage());
		}

	}

	@JsonView(Views.Internal.class)
	@GetMapping("internal/{id}")
	public Optional<User> getUserById2(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFoundException.getMessage());
		}

	}

}
