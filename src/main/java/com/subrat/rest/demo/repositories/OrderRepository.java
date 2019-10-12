package com.subrat.rest.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrat.rest.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
