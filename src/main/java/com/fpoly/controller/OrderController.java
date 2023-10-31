package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
	@GetMapping("")
	public String getOrder() {
		return "order";
	}
	@GetMapping("/success")
	public String successOrder() {
		return "orderSuccess";
	}
}
