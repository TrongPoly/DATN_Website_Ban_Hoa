package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer>{

}
