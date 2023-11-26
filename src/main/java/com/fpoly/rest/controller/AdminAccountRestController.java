package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Account;
import com.fpoly.repository.AccountRepository;
import com.fpoly.service.AccountService;
import com.fpoly.service.SessionService;

@RestController
@RequestMapping("/rest")
public class AdminAccountRestController {

	@Autowired
	AccountRepository accountrep;

	@Autowired
	SessionService sessionService;

	@Autowired
	AccountService accountService;

	@GetMapping("/account")
	public ResponseEntity<List<Account>> getAll(Model model) {
		return ResponseEntity.ok(accountService.findAll());
	}

	@PostMapping("/account")
	public Account post(@RequestBody Account ac) {
		accountService.saveAccount(ac);
		return ac;
	}

	@GetMapping("/account/{id}")
	public ResponseEntity<Account> getOne(@PathVariable("id") String id) {

		if (!accountrep.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(accountrep.findById(id).get());
	}

	@PutMapping("/account/{id}")
	public Account put(@PathVariable("id") String id, @RequestBody Account ac) {

		accountService.saveAccount(ac);
		return ac;
	}

	@PutMapping("/account/chan/{id}")
	public ResponseEntity<Account> chan(@PathVariable("id") String id) {
		Account account = accountService.findByid(accountrep.findById(id).get().getEmail());
		account.setLocked(true);
		accountService.saveAccount(account);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/account/boChan/{id}")
	public ResponseEntity<Account> boChan(@PathVariable("id") String id) {
		Account account = accountService.findByid(accountrep.findById(id).get().getEmail());
		account.setLocked(false);
		accountService.saveAccount(account);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/account/search")
	public List<Account> searchAccountByName(@RequestParam("keyword") String keyword) {
		return accountService.searchByName(keyword);
	}
}
