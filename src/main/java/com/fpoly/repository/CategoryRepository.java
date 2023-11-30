package com.fpoly.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query(name = "findByFullName")
	List<Category> findByName(String tenLoaiHang,Pageable page);
	List<Category> findByNameContaining(String keyword);
}
