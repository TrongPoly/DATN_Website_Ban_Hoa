package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.ReportProduct;

public interface ReportProductRepository extends JpaRepository<ReportProduct, String> {
	@Query("SELECT NEW ReportProduct(od.product.name,COUNT(od.product.name), SUM(od.quantity * od.price) ) " + "FROM Order o, OrderDetail od  "
			+ "WHERE o.id = od.order.id AND o.status.statusId =3  AND YEAR(o.orderDate) = :year AND MONTH(o.orderDate) = :month "
			+ "GROUP BY od.product.name")
	List<ReportProduct> reportProduct(int month, int year);
}
