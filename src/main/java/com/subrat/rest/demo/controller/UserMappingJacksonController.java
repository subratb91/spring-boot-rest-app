package com.subrat.rest.demo.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.subrat.rest.demo.entities.User;
import com.subrat.rest.demo.exceptions.UserNotFoundException;
import com.subrat.rest.demo.service.UserService;

@RestController
@RequestMapping(value = "/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;

	// getUserById - fields with hashset
	@GetMapping("{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();

			// Only the below fields added in Set will be included in Json Response
			Set<String> filterFields = new HashSet<>();
			filterFields.add("id");
			filterFields.add("username");
			filterFields.add("ssn");
			filterFields.add("orders");

			FilterProvider filterprovider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(filterFields));

			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterprovider);

			return mapper;
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFoundException.getMessage());
		}

	}

	// getUserById - fields with @RequestParam
	@GetMapping("params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id,
			@RequestParam("fields") Set<String> filterFields) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();

			FilterProvider filterprovider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(filterFields));

			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterprovider);

			return mapper;
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFoundException.getMessage());
		}
	}

}
