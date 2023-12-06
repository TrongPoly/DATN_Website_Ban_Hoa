package com.fpoly.DTO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.model.ReportCost;

@Repository
public interface ReportCostRepo extends JpaRepository<ReportCost, Date>{
	@Query("SELECT NEW ReportCost(o.order_date, COUNT(DISTINCT o.order_id), SUM(od.quantity * od.price) ) "
			+ "FROM _order o, order_details od  "
			+ "WHERE o.order_id = od.order_id AND MONTH(o.order_date) = :month "
			+ "GROUP BY order_date")
	List<ReportCost> reportCost(@Param("month") Integer month);
}
