package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Category;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.Product;

public interface ProductService {
	List<Product> findAllSP(Boolean ascending);
	
	Product findById(Integer idProduct);

	List<Product> findByCategory(Category category);

	void saveProduct(Product product);

	Product findByOrderDetails(OrderDetail ordDtail);
}
