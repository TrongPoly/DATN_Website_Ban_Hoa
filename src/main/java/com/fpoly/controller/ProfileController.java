package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
	@GetMapping("/Profile")
	public String profile() {
		return "Profile";
	}
}
