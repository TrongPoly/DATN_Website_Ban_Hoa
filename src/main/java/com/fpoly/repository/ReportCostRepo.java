package com.fpoly.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fpoly.model.ReportCost;

public interface ReportCostRepo extends JpaRepository<ReportCost, Long> {
	@Query("SELECT NEW ReportCost(o.orderDate, COUNT(DISTINCT o.id), SUM(od.quantity * od.price) ) "
			+ "FROM Order o, OrderDetail od  "
			+ "WHERE o.id = od.order.id AND YEAR(o.orderDate) = YEAR(CURRENT_DATE) AND MONTH(o.orderDate) = :month "
			+ "GROUP BY o.orderDate")
	List<ReportCost> reportCost(@Param("month") Integer month);
}
