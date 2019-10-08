package com.subrat.rest.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.subrat.rest.demo.entities.User;
import com.subrat.rest.demo.exceptions.UserExistsException;
import com.subrat.rest.demo.exceptions.UserNameNotFoundException;
import com.subrat.rest.demo.exceptions.UserNotFoundException;
import com.subrat.rest.demo.service.UserService;

@RestController
@RequestMapping("users")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
		try {
			userService.createUser(user);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
			
		} catch (UserExistsException userExistsException) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, userExistsException.getMessage());
		}
	}

	@GetMapping("{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFoundException.getMessage());
		}

	}

	@PutMapping("{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, userNotFoundException.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@GetMapping("byUsername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		
		User user = userService.getUserByUsername(username);
		if(user == null) {
			throw new UserNameNotFoundException("Username:"+ username +" not found");
		}
		
		return user;
	}

}
