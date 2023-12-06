package com.fpoly.rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.model.Product;

import com.fpoly.repository.CategoryRepository;
import com.fpoly.repository.ProductRepository;
import com.fpoly.service.ProductService;

import jakarta.servlet.annotation.MultipartConfig;


@RestController
@MultipartConfig
@RequestMapping("/rest")
public class AdminProductRestController {
	@Autowired
	ProductService PrAd;
	@Autowired
	ProductRepository prRep;
	@Autowired
	CategoryRepository catRep;

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProduct() {
		List<Product> listProducts = PrAd.findAllSP(true, 0);
		return ResponseEntity.status(HttpStatus.OK).body(listProducts);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) {
		if (!prRep.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(prRep.findById(id).get());
	}

	@PostMapping("/product")
	public ResponseEntity<Product> post(@RequestBody Product pr) {
		pr.setIsAvailable(true);
		PrAd.saveProduct(pr);
		return ResponseEntity.ok(pr);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Product> put(@RequestBody Product pr, @PathVariable("id") Integer id) {
		PrAd.saveProduct(pr);
		return ResponseEntity.ok(pr);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> DeleteId(@PathVariable("id") Integer id) {

		if (!prRep.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		prRep.deleteById(id);
		return ResponseEntity.ok().build();

	}

	@GetMapping("/product/search")
	public List<Product> searchProductByName(@RequestParam("keyword") String keyword) {
		return PrAd.searchByName(keyword);
	}

	@PutMapping("/account/kinhDoanh/{id}")
	public ResponseEntity<Product> chan(@PathVariable("id") Integer id) {
		Product product = PrAd.findById(prRep.findById(id).get().getId());
		product.setIsAvailable(true);
		PrAd.saveProduct(product);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/account/ngungKinhDoanh/{id}")
	public ResponseEntity<Product> boChan(@PathVariable("id") Integer id) {
		Product product = PrAd.findById(prRep.findById(id).get().getId());
		product.setIsAvailable(false);
		PrAd.saveProduct(product);
		return ResponseEntity.ok().build();
	}
}
