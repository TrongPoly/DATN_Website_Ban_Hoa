package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/index")
	public String index () {
		return "index";
	}
	@GetMapping("/contact")
	public String contact () {
		return "contact";
	}
	@GetMapping("/product")
	public String product () {
		return "shop";
	}

	@GetMapping("/about")
	public String about () {
		return "about";
	}
}
