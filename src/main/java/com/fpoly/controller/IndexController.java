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
			return "redirect:/admin/order";
		}
		return "index";
	}

	@GetMapping("/contact")
	public String contact() {
		return "face";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}
}
