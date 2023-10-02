package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
