package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Category;
import com.fpoly.model.Product;
import java.math.BigDecimal;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByCategory(Category category);
}
