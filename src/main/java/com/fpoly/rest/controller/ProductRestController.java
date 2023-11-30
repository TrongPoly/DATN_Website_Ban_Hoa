package com.fpoly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.DTO.OrderDTO;
import com.fpoly.model.Product;
import com.fpoly.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductRestController {
	@Autowired
	ProductService productService;

	@GetMapping("")
	public ResponseEntity<List<Product>> getProduct(@RequestParam("ascending") Optional<Boolean> ascending) {
		List<Product> listProducts = productService.findAllSP(ascending.orElse(null));
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

	@GetMapping("/category/{idProduct}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("idProduct") Integer idProduct) {
		Product product = productService.findById(idProduct);

		if (product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		List<Product> listProduct = productService.findByCategory(product.getCategory());
		return ResponseEntity.status(HttpStatus.OK).body(listProduct);
	}
	
	@PutMapping("/update/after_order")
	public ResponseEntity<?> updateAfterOrder(@RequestBody List<OrderDTO> orderDTOs){
		for (OrderDTO orderDTO : orderDTOs) {
			Product product = productService.findById(orderDTO.getId());
			System.out.println(product.getQuantity());
			product.setQuantity(product.getQuantity() - orderDTO.getQuant());
			productService.saveProduct(product);
			System.out.println(product.getQuantity());
		}
		return ResponseEntity.ok().build();
	}
}
