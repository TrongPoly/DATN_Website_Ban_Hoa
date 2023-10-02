package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
