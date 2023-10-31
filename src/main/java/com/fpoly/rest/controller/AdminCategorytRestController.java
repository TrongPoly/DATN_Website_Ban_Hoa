package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Category;
import com.fpoly.repository.CategoryRepository;

@RestController

@RequestMapping("/rest")
public class AdminCategorytRestController {
	@Autowired
	CategoryRepository catRep;
	
	
	@GetMapping("/category")
	public List<Category> getAll (Model model){
		return catRep.findAll();
	}
	
	
}
