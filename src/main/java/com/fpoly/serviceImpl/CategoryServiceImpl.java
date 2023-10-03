package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Category;
import com.fpoly.repository.CategoryRepository;
import com.fpoly.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}

}
