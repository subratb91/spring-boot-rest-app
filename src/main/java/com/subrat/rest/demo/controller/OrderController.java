package com.subrat.rest.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.subrat.rest.demo.entities.Order;
import com.subrat.rest.demo.entities.User;
import com.subrat.rest.demo.exceptions.OrderNotFoundException;
import com.subrat.rest.demo.exceptions.UserNotFoundException;
import com.subrat.rest.demo.repositories.OrderRepository;
import com.subrat.rest.demo.repositories.UserRepository;

@RestController
@RequestMapping("users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("{userId}/orders")
	public List<Order> getAllOrdersByUserId(@PathVariable("userId") Long userId) throws UserNotFoundException{
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User with id=" + userId +" not found");
		}
		return userOptional.get().getOrders();
	}
	
	@PostMapping("{userId}/orders")
	public void createOrderByUserId(@PathVariable("userId") Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User with id=" + userId +" not found");
		}
		
		User user = userOptional.get();
		order.setUser(user);
		orderRepository.save(order);
		
	}
	
	@GetMapping("{userId}/orders/{orderId}")
	public Order getOrderbyOrderId(@PathVariable("userId") Long userId,@PathVariable("orderId") Long orderId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id=" + userId +" not found");
		}
		List<Order> orders = userOptional.get().getOrders();
		Optional<Order> optionalOrderDetail = orders.stream().filter(order -> order.getOrderId().equals(orderId)).findFirst();
		if(!optionalOrderDetail.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with orderId=" + orderId +" not found");
		}
		 return optionalOrderDetail.get();
	}

}
