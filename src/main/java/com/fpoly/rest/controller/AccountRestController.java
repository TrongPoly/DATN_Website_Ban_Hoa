package com.fpoly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Account;
import com.fpoly.service.SessionService;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	@Autowired
	SessionService sessionService;

	@GetMapping("/userLogin")
	public Account getUser() {
		Account account = sessionService.getSession("user");
		if (account == null) {
			return null;
		}
		System.out.println(account.getEmail());
		return account;
	}
}
