package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{

}
