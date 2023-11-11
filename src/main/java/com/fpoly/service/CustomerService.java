package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Account;
import com.fpoly.model.Customer;

public interface CustomerService {
	List<Customer> findAllCustomer();
	
	Customer findById(Integer idCustomer);
	
	Customer findByEmail(Account email);
	
	void saveCustomer(Customer customer);
	
	Customer findByUser();
	
	List<Customer> searchByName(String keyword);
}
