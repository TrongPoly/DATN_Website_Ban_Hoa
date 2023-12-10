package com.fpoly.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpoly.model.Account;
import com.fpoly.model.Order;
import com.fpoly.model.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByEmail(Account email);

//	List<Order> findByStatus(OrderStatus status);
	List<Order> findByStatus(OrderStatus status, Sort sort);

	@Query("SELECT o FROM Order o WHERE MONTH(o.orderDate) = :month AND YEAR(o.orderDate) = :year")
	List<Order> findOrderInMonth(@Param("month") Integer month,@Param("year") Integer year);

	@Query("SELECT o FROM Order o WHERE CAST(o.id AS string) LIKE %:key%")
	List<Order> findByIdContains(@Param("key") Integer key);

	@Query("SELECT o FROM Order o WHERE o.email.email LIKE %:key%")
	List<Order> findByEmailContains(@Param("key") String key);
	
	@Query("select distinct YEAR(o.orderDate) from Order o")
	List<Integer> getOrderYear();
}
