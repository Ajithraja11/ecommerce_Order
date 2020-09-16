package com.ecommerce.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/order/{orderID}")
	public ResponseEntity<Object> getOrderByOrderID(@PathVariable int orderID)
	{
		log.info("Inside Controller (getOrderbyOrderID) to get order for the ID "+orderID);
		return orderService.getOrderbyID(orderID);
	}
	
	@PostMapping("/order")
	public ResponseEntity<Object> createNewOrder(@RequestBody Order neworder)
	{
		log.info("Inside Controller (createNewOrder) to create a new order");
		return orderService.createOrder(neworder);
		
	}
	
	@DeleteMapping("/order/{orderID}")
	public ResponseEntity<Object> cancelOrderByOrderID(@PathVariable int orderID)
	{
		log.info("Inside Controller (cancelOrderID) to cancel the order for the ID "+orderID); 
		return orderService.deletebyID(orderID);
	}
	
}
