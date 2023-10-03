package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Category;
import com.fpoly.model.Product;
import com.fpoly.repository.ProductRepository;
import com.fpoly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> findAllSP() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Integer idProduct) {
		return productRepository.findById(idProduct).orElse(null);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

}
