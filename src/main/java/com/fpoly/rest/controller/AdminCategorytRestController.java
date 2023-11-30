package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Account;
import com.fpoly.model.Category;
import com.fpoly.repository.CategoryRepository;
import com.fpoly.service.CategoryService;

@RestController
@RequestMapping("/rest")
public class AdminCategorytRestController {
	@Autowired
	CategoryRepository catRep;
	
	@Autowired
	CategoryService categoryservice;
	
	
	@GetMapping("/category")
	public List<Category> getAll (Model model){
		return catRep.findAll();
	}
	
	@PostMapping("/category")
	public Category post(@RequestBody Category cat) {
		categoryservice.saveAccount(cat);
		return cat;
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getOne(@PathVariable("id") Integer id) {

		if (!catRep.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(catRep.findById(id).get());
	}

	@PutMapping("/category/{id}")
	public Category put(@PathVariable("id") Integer id, @RequestBody Category cat) {

		categoryservice.saveAccount(cat);
		return cat;
	}

	@GetMapping("/category/search")
	public List<Category> searchAccountByName(@RequestParam("keyword") String keyword) {
		return categoryservice.searchByName(keyword);
	}
	
	
}
