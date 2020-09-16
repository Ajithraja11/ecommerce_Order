package com.ecommerce.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.entity.Order;

public interface OrderDAO extends JpaRepository<Order,Integer> {

}
