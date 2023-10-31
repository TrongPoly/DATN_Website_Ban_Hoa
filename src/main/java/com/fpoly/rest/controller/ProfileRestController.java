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

import com.fpoly.model.Customer;

import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;



@RestController
@RequestMapping("/rest")
public class ProfileRestController {
	@Autowired
	CustomerService customerService;

	@Autowired
	SessionService session;


	@GetMapping("/profile/getUser")
	public Customer findUser() {
		return customerService.findByUser();
	}

	@GetMapping("/profile/{id}")
	public Customer getOne(@PathVariable("id") Integer id) {
		return customerService.findById(id);
	}
	
	@PostMapping("/profile")
	public Customer post(@RequestBody Customer kh) {
		customerService.saveCustomer(kh);
		return kh;
	}

	@PutMapping("/profile/{id}")
	public ResponseEntity<Customer> put(@PathVariable("id") Integer id, @RequestBody Customer kh) {
		Customer khachHang = customerService.findById(id);
		if (khachHang != null)
			customerService.saveCustomer(kh);
		return ResponseEntity.ok(kh);
	}
}
