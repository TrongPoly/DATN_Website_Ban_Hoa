package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Category;
import com.fpoly.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByCategory(Category category);
}
