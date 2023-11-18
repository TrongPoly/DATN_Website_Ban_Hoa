package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Customer;
import com.fpoly.model.Order;
import com.fpoly.model.OrderStatus;

public interface OrderService {
	
	List<Order> findAllOrder();
	
	List<Order> findByCustomer(Customer customer);
	
	Order findById(Integer id);

	void saveOrder(Order order);
	
	List<Order> findByStatus(OrderStatus status);
}