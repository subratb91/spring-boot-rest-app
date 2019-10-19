package com.subrat.rest.demo.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrat.rest.demo.dtos.UserDtoV1;
import com.subrat.rest.demo.dtos.UserDtoV2;
import com.subrat.rest.demo.entities.User;
import com.subrat.rest.demo.exceptions.UserNotFoundException;
import com.subrat.rest.demo.service.UserService;

@RestController
@RequestMapping(value = "/versioning/header/users")
@Validated
public class UserCustomHeaderVersioningController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "{id}", headers = "API-VERSION=1")
	public UserDtoV1 getUserById1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User with id=" + id + " not found");
		}

		User user = userOptional.get();
		UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);

		return userDtoV1;

	}

	@GetMapping(value = "{id}", headers = "API-VERSION=2")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User with id=" + id + " not found");
		}

		User user = userOptional.get();
		UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);

		return userDtoV2;

	}

}
