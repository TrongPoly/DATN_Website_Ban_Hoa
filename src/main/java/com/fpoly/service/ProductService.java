package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Category;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.Product;


public interface ProductService {
	List<Product> findAllSP(Boolean ascending, Integer idCategory);
	
	Product findById(Integer idProduct);

	List<Product> findByCategory(Category category);

	void saveProduct(Product product);
	
	List<Product> findAllByCategory(String CategoryName);

	List<Product> searchByName(String keyword);

	Product findByOrderDetails(OrderDetail ordDtail);
}
