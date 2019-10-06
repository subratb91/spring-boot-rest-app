package com.subrat.rest.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.subrat.rest.demo.entities.User;
import com.subrat.rest.demo.exceptions.UserExistsException;
import com.subrat.rest.demo.exceptions.UserNotFoundException;
import com.subrat.rest.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new UserExistsException("User already exists. PLease choose another username");
		}
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found with id : " + id);
		}
		return user;
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> userFromDb = userRepository.findById(id);
		if (!userFromDb.isPresent()) {
			throw new UserNotFoundException("User not found with id : " + id + ". Please provide correct user id");
		}

		/*
		 * if(!userRepository.existsById(id)) { throw new
		 * UserNotFoundException("User not found with id : " + id
		 * +". Please provide correct user id"); }
		 */

		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserById(Long id) {
		Optional<User> userFromDb = userRepository.findById(id);
		if (!userFromDb.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User not found with id : " + id + ". Please provide correct user id");
		}
		userRepository.deleteById(id);
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
