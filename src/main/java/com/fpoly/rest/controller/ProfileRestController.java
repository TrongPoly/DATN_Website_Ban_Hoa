package com.fpoly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Account;
import com.fpoly.service.AccountService;
import com.fpoly.service.SessionService;

@RestController
@RequestMapping("/rest")
public class ProfileRestController {

	@Autowired
	SessionService session;

	@Autowired
	AccountService accountService;

	@GetMapping("/profile/getUser/{email}")
	public Account findUser(@PathVariable("email") String email) {
		return accountService.findByid(email);
	}

	@GetMapping("/profile/{id}")
	public Account getOne(@PathVariable("id") String id) {
		return accountService.findByid(id);
	}

	@PostMapping("/profile")
	public Account post(@RequestBody Account ac) {
		accountService.saveAccount(ac);
		return ac;
	}

	@PutMapping("/profile/update")
	public ResponseEntity<Account> put(@RequestBody Account ac) {
		accountService.saveAccount(ac);
		return ResponseEntity.ok(ac);
	}

	@PutMapping("/profile/change_password/{email}")
	public ResponseEntity<Account> changePassword(@PathVariable("email") String email,
			@RequestParam("oldPw") String oldPw, @RequestParam("newPw") String newPw) {
		Account account = accountService.findByid(email);
		if (account.getPassword().equals(oldPw)) {
			account.setPassword(newPw);
			accountService.saveAccount(account);
			return ResponseEntity.ok(account);
		}
		return ResponseEntity.badRequest().build();
	}
}
