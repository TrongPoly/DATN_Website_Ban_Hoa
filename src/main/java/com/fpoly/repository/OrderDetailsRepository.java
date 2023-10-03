package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Order;
import com.fpoly.model.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer>{
	List<OrderDetail> findByOrder(Order order);
}
