package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("")
	public String index () {
		return "shop";
	}
	@GetMapping("/shopSingle")
	public String shopSingle () {
		return "shop-single";
	}
	@GetMapping("/informationCart")
	public String informationCart () {
		return "informationCart";
	}
	@GetMapping("/contact")
	public String contact () {
		return "contact";
	}
	@GetMapping("/checkout")
	public String checkout () {
		return "checkout";
	}
	@GetMapping("/billChange")
	public String billChange () {
		return "billChange";
	}
	@GetMapping("/cart")
	public String cart () {
		return "cart";
	}
	@GetMapping("/about")
	public String about () {
		return "about";
	}
}
