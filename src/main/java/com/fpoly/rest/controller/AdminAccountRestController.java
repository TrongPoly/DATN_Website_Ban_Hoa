package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Account;
import com.fpoly.model.Product;

import com.fpoly.repository.AccountRepository;
import com.fpoly.repository.ProductRepository;
import com.fpoly.service.ProductService;



@RestController
@RequestMapping("/api")
public class AdminAccountRestController {
	@Autowired
	AccountRepository accRep;
		
	@GetMapping("/taikhoan")
	public ResponseEntity<List<Account>> getAll(Model model){
		return ResponseEntity.ok(accRep.findAll());
	}

}
