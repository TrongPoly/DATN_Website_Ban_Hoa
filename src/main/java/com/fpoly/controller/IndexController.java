package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("")
	public String index () {
		return "shop";
	}
	@GetMapping("/contact")
	public String contact () {
		return "contact";
	}

	@GetMapping("/about")
	public String about () {
		return "about";
	}
}
