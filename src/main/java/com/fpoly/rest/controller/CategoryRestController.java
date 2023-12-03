package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Category;
import com.fpoly.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public ResponseEntity<List<Category>> getAll() {
		List<Category> listCategory = categoryService.findAllCategory();
		return ResponseEntity.ok(listCategory);
	}
}
