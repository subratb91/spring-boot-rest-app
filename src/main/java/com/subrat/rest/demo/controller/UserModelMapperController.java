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

import com.subrat.rest.demo.dtos.UserMmDto;
import com.subrat.rest.demo.entities.User;
import com.subrat.rest.demo.exceptions.UserNotFoundException;
import com.subrat.rest.demo.service.UserService;

@RestController
@RequestMapping(value = "/modelmapper/users")
@Validated
public class UserModelMapperController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User with id=" + id + " not found");
		}

		User user = userOptional.get();
		UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);

		return userMmDto;

	}

}
