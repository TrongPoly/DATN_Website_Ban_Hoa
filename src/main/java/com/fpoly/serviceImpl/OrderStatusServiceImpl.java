package com.fpoly.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.OrderStatus;
import com.fpoly.repository.OrderStatusRepository;
import com.fpoly.service.OrderStatusService;
@Service
public class OrderStatusServiceImpl implements OrderStatusService{
	@Autowired
	OrderStatusRepository orderStatusRepository;
	
	@Override
	public OrderStatus findById(int id) {
		return orderStatusRepository.findById(id).orElse(null);
	}

}
