package com.fpoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/auth")
public class AccountController {
	@Autowired
	SessionService session;
	@Autowired
	CustomerService customer;
	@Autowired
	ServletContext context;

	@GetMapping("/login_form")
	public String formLogin() {
		return "login";
	}

	@GetMapping("/register/form")
	public String formRegis() {
		return "User/register";
	}

	@GetMapping("/login/success")
	public String doLogin() {
		if (session.getSession("user").getRole().getRoleName().equals("Admin")) {
			return "redirect:/admin";
		}
		return "redirect:/";
	}

	@RequestMapping("/logoff/success")
	public String doLogout(Model model) {
		session.removeSession("user");
		return "redirect:/auth/login_form";
	}

	@RequestMapping("/login/error")
	public String loginError() {
		return "redirect:/auth/login_form";
	}

//	@RequestMapping("/login/blocked")
//	public String isBlocked() {
//		context.setAttribute("msg", "Tài khoản đã bị khóa!");
//		return "redirect:/auth/login/form";
//	}

	@RequestMapping("/access/denied")
	public String denied() {
		return "User/ErrorPage";
	}

}
