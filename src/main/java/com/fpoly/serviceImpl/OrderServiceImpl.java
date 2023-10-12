package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Customer;
import com.fpoly.model.Order;
import com.fpoly.repository.OrderRepository;
import com.fpoly.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> findAllOrder() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> findByCustomer(Customer customer) {
		return orderRepository.findByCustomer(customer);
	}

	@Override
	public Order findById(Integer id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

}
