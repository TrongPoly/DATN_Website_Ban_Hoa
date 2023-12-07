package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.model.Account;
import com.fpoly.model.Order;
import com.fpoly.model.OrderStatus;
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
	public List<Order> findByCustomer(Account email) {
		return orderRepository.findByEmail(email);
	}

	@Override
	public Order findById(Integer id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}


	@Override
	public List<Order> findByStatus(OrderStatus status) {
		return orderRepository.findByStatus(status,Sort.by(Sort.Order.desc("id")));
	}

	@Override
	public List<Order> findOrderInMonth(Integer month) {
		return orderRepository.findOrderInMonth(month);
	}

}
