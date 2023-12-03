package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
	@GetMapping("/index")
	public String listProduct() {
		return "shop";
	}
	
	@GetMapping("/details")
	public String productDetails() {
		return "shop-single";
	}
	@GetMapping("/refresh")
	public String productrefresh() {
		return "shop-single";
	}
	@GetMapping("/category")
	public String findAllByCategory(@RequestParam("id") Integer idCategory) {
	return "shop";	
	}
}
