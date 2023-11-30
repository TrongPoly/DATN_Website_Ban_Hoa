package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.fpoly.model.Category;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.Product;
import com.fpoly.repository.ProductRepository;
import com.fpoly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> findAllSP(Boolean ascending) {
		if(ascending==null) {
			return productRepository.findAll();
		}
		String property = "price"; 
        Sort.Order order = ascending ? Sort.Order.asc(property) : Sort.Order.desc(property);
        return productRepository.findAll(Sort.by(order));
	}

	@Override
	public Product findById(Integer idProduct) {
		return productRepository.findById(idProduct).orElse(null);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product findByOrderDetails(OrderDetail ordDtail) {
		// TODO Auto-generated method stub
		return null;
	}

}
