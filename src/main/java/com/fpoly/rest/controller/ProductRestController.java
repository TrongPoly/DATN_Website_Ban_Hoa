package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Product;
import com.fpoly.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductRestController {
	@Autowired
	ProductService productService;

	@GetMapping("")
	public ResponseEntity<List<Product>> getProduct() {
		List<Product> listProducts = productService.findAllSP();
		return ResponseEntity.status(HttpStatus.OK).body(listProducts);
	}

	@GetMapping("/{idProduct}")
	public ResponseEntity<Product> getOneProduct(@PathVariable("idProduct") Integer idProduct) {
		Product product = productService.findById(idProduct);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
}
