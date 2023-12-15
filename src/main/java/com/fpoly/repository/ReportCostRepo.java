package com.fpoly.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fpoly.model.ReportCost;
import com.fpoly.model.ReportProduct;

public interface ReportCostRepo extends JpaRepository<ReportCost, Long> {
	@Query("SELECT NEW ReportCost(o.orderDate, COUNT(DISTINCT o.id), SUM(od.quantity * od.price) ) "
			+ "FROM Order o, OrderDetail od  "
			+ "WHERE o.id = od.order.id AND o.status.statusId =3  AND YEAR(o.orderDate) = :year AND MONTH(o.orderDate) = :month "
			+ "GROUP BY o.orderDate")
	List<ReportCost> reportCost(@Param("month") Integer month, @Param("year") Integer year);

	
}
