package com.fpoly.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.Customer;
import com.fpoly.model.Account;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByEmail(Account email);
	
	@Query(name = "findByFullName")
	List<Customer> findByFullName(String tenKhachHang,Pageable page);

	List<Customer> findByFullNameContaining(String keyword);
}
