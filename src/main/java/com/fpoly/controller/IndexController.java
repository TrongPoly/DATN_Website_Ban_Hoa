package com.fpoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.service.SessionService;

@Controller
public class IndexController {
	@Autowired
	SessionService session;
	
	@GetMapping("/index")
	public String index() {
		if(session.getSession("user").getRole().getId()==1) {
			System.out.println("ALO");
			return "redirect:/admin/order";
		}
		System.out.println("OLA");
		return "index";
	}

	@GetMapping("/contact")
	public String contact() {
		return "face";
	}

	@GetMapping("/product")
	public String product() {
		return "shop";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}
}
