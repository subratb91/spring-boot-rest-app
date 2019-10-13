package com.subrat.rest.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue
	@JsonView(Views.Internal.class)
	private Long orderId;
	//@JsonView(Views.Internal.class) // Commented to not see this field in JSON response
	private String orderDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;


}
