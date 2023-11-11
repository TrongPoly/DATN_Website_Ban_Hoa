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
import com.fpoly.model.Customer;
import com.fpoly.repository.CustomerRepository;
import com.fpoly.service.AccountService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

@RestController
@RequestMapping("/rest")
public class AdminCustomerRestController {
	@Autowired
	CustomerRepository daokh;
	
	@Autowired
	SessionService sessionService;

	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAll(Model model) {
		return ResponseEntity.ok(customerService.findAllCustomer());
	}
		
	@PutMapping("/customer/chan/{id}")
	public  ResponseEntity<Customer> chan(@PathVariable("id") Integer id) {
		Account account = accountService.findByid(daokh.findById(id).get().getEmail().getEmail());
		account.setActive(false);
		accountService.saveAccount(account);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/customer/boChan/{id}")
	public  ResponseEntity<Customer> boChan(@PathVariable("id") Integer id) {
		Account account = accountService.findByid(daokh.findById(id).get().getEmail().getEmail());
		account.setActive(true);
		accountService.saveAccount(account);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/customer/search")
	public List<Customer> searchCustomerByName(@RequestParam("keyword") String keyword){
		return customerService.searchByName(keyword);
	}
}
