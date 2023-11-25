package com.fpoly.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Account;
import com.fpoly.model.Order;
import com.fpoly.model.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByEmail(Account email);
	
//	List<Order> findByStatus(OrderStatus status);
	List<Order> findByStatus(OrderStatus status, Sort sort);
}
