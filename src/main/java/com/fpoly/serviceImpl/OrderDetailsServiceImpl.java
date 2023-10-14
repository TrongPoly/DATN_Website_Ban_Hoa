package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Order;
import com.fpoly.model.OrderDetail;
import com.fpoly.repository.OrderDetailsRepository;
import com.fpoly.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	@Override
	public List<OrderDetail> findAllOrderDetails() {
		return orderDetailsRepository.findAll();
	}

	@Override
	public List<OrderDetail> findByOrder(Order order) {
		return orderDetailsRepository.findByOrder(order);
	}

	@Override
	public OrderDetail findById(Integer id) {
		return orderDetailsRepository.findById(id).orElse(null);
	}

	@Override
	public void saveAll(List<OrderDetail> orderDetails) {
		orderDetailsRepository.saveAll(orderDetails);
	}
	

}
