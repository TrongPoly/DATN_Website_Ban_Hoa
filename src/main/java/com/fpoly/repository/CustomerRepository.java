package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Customer;
import com.fpoly.model.Account;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByEmail(Account email);
}
