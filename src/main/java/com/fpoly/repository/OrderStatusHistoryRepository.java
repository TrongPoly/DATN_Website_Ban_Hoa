package com.fpoly.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Order;
import com.fpoly.model.OrderStatusHistory;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Integer>{
	List<OrderStatusHistory> findByOrder(Order order, Sort sort);
}
