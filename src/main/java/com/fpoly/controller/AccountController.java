package com.fpoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.Account;
import com.fpoly.model.Customer;
import com.fpoly.service.AccountService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.RoleService;
import com.fpoly.service.SessionService;
//import com.fpoly.service.UserInfoService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/auth")
public class AccountController {
	@Autowired
	SessionService session;
	@Autowired
	ServletContext context;
	@Autowired
	AccountService accountService;
	@Autowired
	SessionService sessionService;
	@Autowired
	RoleService roleService;
	@Autowired
	CustomerService customerService;
	

	// Xác minh tài khoản thành công
	@GetMapping("/success_verify")
	public String success_verify() {
		return "login";
	}

	// Tài khoản đã được xác minh trước đó
	@GetMapping("/determined")
	public String determined() {
		return "login";
	}

	@GetMapping("/login")
	public String formLogin() {
		return "login";
	}

	@GetMapping("/register")
	public String formRegister() {
		return "register";
	}

	@GetMapping("/login/success")
	public String doLogin() {
		return "redirect:/index";
	}

	@RequestMapping("/logoff/success")
	public String doLogout(Model model) {
		session.removeSession("user");
		return "redirect:/";
	}

	@RequestMapping("/login/error")
	public String loginError() {
		return "redirect:/auth/login";
	}

	@RequestMapping("/blocked")
	public String isBlocked() {
		return "login";
	}

	@RequestMapping("/access/denied")
	public String denied() {
		return "error_page/error-500";
	}

	@RequestMapping("/verify_email")
	public String verifyEmail() {
		return "verifyEmail";
	}

	@RequestMapping("/confirm_otp")
	public String confirmOtp() {
		return "confirm_otp";
	}

	@RequestMapping("/changePassword")
	public String changePassword() {
		return "changePassword";
	}

	@RequestMapping("/success_change_pw")
	public String successChangePassword() {
		return "login";
	}

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		Account account = accountService.findByid(email);
		if (account != null) {
			sessionService.setSession("user", account, 300);
		} else {
			String password = Long.toHexString(System.currentTimeMillis());
			Account account2 = new Account(email, password, roleService.findById(2), true);
			accountService.saveAccount(account2);
			String name = oauth2.getPrincipal().getAttribute("name");
			Customer customer = new Customer(name, account2, "0123456789", true);
			customerService.saveCustomer(customer);
			sessionService.setSession("user", account2, 300);
		}
		return "forward:/auth/login/success";
	}
}
