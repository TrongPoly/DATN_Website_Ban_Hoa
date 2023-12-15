package com.fpoly.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.model.Category;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.Product;
import com.fpoly.repository.CategoryRepository;
import com.fpoly.repository.ProductRepository;
import com.fpoly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Product> findAllSP(Boolean ascending, Integer idCategory) {
	
		if (ascending != null) {
			String property = "price";
			Sort.Order order = ascending ? Sort.Order.asc(property) : Sort.Order.desc(property);
			if (idCategory > 0) {
				Category category = categoryRepository.findById(idCategory).orElse(null);
				return productRepository.findByCategory(category, Sort.by(order));
			}
			return productRepository.findAll(Sort.by(order));
		}else if(idCategory>0) {
			Category category = categoryRepository.findById(idCategory).orElse(null);
			return productRepository.findByCategory(category);
		}
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

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public List<Product> findAllByCategory(String CategoryName) {
		return productRepository.findAll().stream().filter(sv -> sv.getCategory().getName().equals(CategoryName))
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> searchByName(String keyword) {
		return productRepository.findByNameContaining(keyword);
	}

	@Override
	public Product findByOrderDetails(OrderDetail ordDtail) {
		// TODO Auto-generated method stub
		return null;
	}

}
