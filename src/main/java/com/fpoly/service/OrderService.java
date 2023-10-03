package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Customer;
import com.fpoly.model.Order;

public interface OrderService {
	List<Order> findAllOrder();
	
	List<Order> findByCustomer(Customer customer);
	
	Order findById(Integer id);
}