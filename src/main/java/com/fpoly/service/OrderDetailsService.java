package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Order;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.OrderStatus;

public interface OrderDetailsService {
	List<OrderDetail> findAllOrderDetails();
	
	List<OrderDetail> findByOrder(Order order);
	
	OrderDetail findById(Integer id);
	
	void saveAll(List<OrderDetail> orderDetails);
	
	List<OrderDetail> findAllByOrderStatus(Order order,OrderStatus status);

}
