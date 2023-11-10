package com.fpoly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Customer;
import com.fpoly.service.AccountService;
import com.fpoly.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
	@Autowired
	CustomerService customerService;
	@Autowired
	AccountService accountService;

	@GetMapping("/{email}")
	public ResponseEntity<Customer> getOne(@PathVariable("email") String email) {
		Customer customer = customerService.findByEmail(accountService.findByid(email));
		return ResponseEntity.ok(customer);
	}
	@PostMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		customerService.saveCustomer(customer);
		return ResponseEntity.ok(customer);
	}
}
