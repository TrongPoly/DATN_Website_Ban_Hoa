package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Account;
import com.fpoly.model.Customer;
import com.fpoly.repository.CustomerRepository;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	SessionService session;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}
	
	@Override
	public Customer findByUser() {
		return customerRepository.findAll().stream()
				.filter(kh -> kh.getEmail().getEmail().equals(session.getSession("user").getEmail())).findFirst().get();
	}
	
	@Override
	public Customer findById(Integer idCustomer) {
		return customerRepository.findById(idCustomer).orElse(null);
	}

	@Override
	public Customer findByEmail(Account email) {
		return customerRepository.findByEmail(email);

	}

	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	@Override
	public List<Customer> searchByName(String keyword){
		return customerRepository.findByFullNameContaining(keyword);
	}

}
