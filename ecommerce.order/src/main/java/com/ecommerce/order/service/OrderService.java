package com.ecommerce.order.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.repository.OrderDaoImplementation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

	@Autowired
	OrderDaoImplementation orderRepository;
	
	OrderValidator validator=new OrderValidator();
	
	public ResponseEntity<Object> getOrderbyID(int orderID) 
	{
		log.info("Inside Service getOrderbyID");
		try {
			Optional<Order> order=orderRepository.getOrderByID(orderID);
			if(order.isPresent())
				{
				log.info("Order found for the ID "+orderID);
				return 	new ResponseEntity<Object>(order,HttpStatus.ACCEPTED);
				}
			else
				{
				log.warn("Order not found for the id "+orderID);
				return new ResponseEntity<Object>("Order not found for the id "+orderID,HttpStatus.NOT_FOUND);		
				}
		}
		catch(Exception e)
		{
			log.error("Exception occured while retriving data for the id "+orderID);
			return new ResponseEntity<Object>("Exception occured while retriving data from the database for the id "+orderID,HttpStatus.BAD_REQUEST);
		}
		
	}

	public ResponseEntity<Object> deletebyID(int orderID) {
		log.info("Inside Service deletebyID");
		try {
			Optional<Order> order=orderRepository.getOrderByID(orderID);
			if(order.isPresent() && orderRepository.deleteByID(orderID))
				{
				log.info("Order Cancelled for the ID "+orderID);
					return 	new ResponseEntity<Object>("Order Cancelled for the ID "+orderID,HttpStatus.ACCEPTED);
				}
			else
				{
				log.warn("Order not found for the ID "+orderID);
				return new ResponseEntity<Object>("Provided OrderID not found in the database for the ID "+orderID,HttpStatus.NOT_FOUND);		
				}
		}
		catch(Exception e)
		{
			log.error("Exception occured while deleting data for the ID "+orderID);
			return new ResponseEntity<Object>("Exception occured while deleting data from the database for the ID "+orderID,HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Object> createOrder(Order neworder) {
		log.info("Inside Service createNewOrder");
		try {
			if( validator.validateOrder(neworder) && orderRepository.createNewOrder(neworder))
				{
					log.info("Order Created");
					return 	new ResponseEntity<Object>(neworder,HttpStatus.CREATED);
				}
			else
				{
					log.warn("Invalid data provided");
					return new ResponseEntity<Object>("Invalid data provided",HttpStatus.BAD_REQUEST);
				}
			}
			
		catch(Exception e)
		{
			log.error("Exception occured while adding data to the database");
			return new ResponseEntity<Object>("Exception occured while adding data to the database",HttpStatus.BAD_REQUEST);
		}
		
	}

	

}
