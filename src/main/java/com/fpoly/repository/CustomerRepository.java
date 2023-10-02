package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
