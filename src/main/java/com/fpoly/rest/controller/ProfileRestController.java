package com.fpoly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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


	@GetMapping("/profile/getUser")
	public Account findUser() {
		return accountService.findByUser();
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

	@PutMapping("/profile/{id}")
	public ResponseEntity<Account> put(@PathVariable("id") String id, @RequestBody Account ac) {
		Account account = accountService.findByid(id);
		if (account != null)
			accountService.saveAccount(ac);
		return ResponseEntity.ok(ac);
	}
}
