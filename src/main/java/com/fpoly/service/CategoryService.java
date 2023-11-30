package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Category;

public interface CategoryService {
	List<Category> findAllCategory();

	void saveAccount(Category category);

	List<Category> searchByName(String keyword);
}
