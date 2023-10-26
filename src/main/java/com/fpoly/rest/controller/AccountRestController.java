package com.fpoly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fpoly.model.Account;
import com.fpoly.model.Customer;
import com.fpoly.service.AccountService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.RoleService;
import com.fpoly.service.SessionService;
import com.fpoly.serviceImpl.MailService;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	@Autowired
	SessionService sessionService;
	@Autowired
	AccountService accountService;
	@Autowired
	RoleService roleService;
	@Autowired
	CustomerService customerService;
	@Autowired
	MailService mailService;

	@GetMapping("/userLogin")
	public Account getUser() {
		Account account = sessionService.getSession("user");
		if (account == null) {
			return null;
		}
		return account;
	}

	@GetMapping("/account/verify/{email}")
	public RedirectView verifyEmail(@PathVariable("email") String email) {
		Account account = accountService.findByid(email);
		// Tài khoản đã được kích hoạt
		if (account.getActive()) {
			return new RedirectView("/auth/determined");
		}
		account.setActive(true);
		accountService.saveAccount(account);
		return new RedirectView("/auth/success_verify");
	}

	@PostMapping("/account/register")
	public ResponseEntity<Account> register(@RequestBody Customer customer, @RequestParam("email") String email,
			@RequestParam("password") String pass) {
		if (accountService.findByid(email) != null) {
			return ResponseEntity.badRequest().build();
		}
		Account account = new Account(email, pass, roleService.findById(2), false);
		accountService.saveAccount(account);
		customer.setEmail(account);
		customerService.saveCustomer(customer);
		try {
			String url = "http://localhost:8080/api/account/verify/" + email;
			mailService.activeAccountEmail(email, customer.getFullName(), url);
			System.out.println(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}
}
