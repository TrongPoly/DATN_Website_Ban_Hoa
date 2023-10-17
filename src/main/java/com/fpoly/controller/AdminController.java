package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/account")
	public String getAccount() {
		return "admin/FormAccount";
	}
	
	@GetMapping("/category")
	public String getCategory() {
		return "admin/FormCategory";
	}
	@GetMapping("/order")
	public String getOrder() {
		return "admin/FormOrder";
	}
	@GetMapping("/product")
	public String getProduct() {
		return "admin/FormProduct";
	}
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
}
