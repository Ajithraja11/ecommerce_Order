package com.ecommerce.order.repository;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.dao.OrderDAO;
import com.ecommerce.order.entity.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class OrderDaoImplementation {

	@Autowired
	OrderDAO orderDao;
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public Optional<Order> getOrderByID(int orderID)throws Exception  
	{
		log.info("Inside Repository to get order for the ID "+orderID);
		return orderDao.findById(orderID);
	}

	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public boolean deleteByID(int orderID)throws Exception 
	{
		log.info("Inside repository to delete order for the ID "+orderID);
		orderDao.deleteById(orderID);
		return true;
	}
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public boolean createNewOrder(Order newOrder)throws Exception 
	{
		log.info("Inside Repository to create a new order");
		orderDao.save(newOrder);
		return true;
	}

}
