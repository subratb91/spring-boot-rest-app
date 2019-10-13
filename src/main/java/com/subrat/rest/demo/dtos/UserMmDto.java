package com.subrat.rest.demo.dtos;

import java.util.List;

import com.subrat.rest.demo.entities.Order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserMmDto {

	private Long id;
	private String username;
	private String firstName;
	private List<Order> orders;

}
