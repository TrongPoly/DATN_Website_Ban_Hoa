package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Account;

public interface AccountService {
	Account findByid(String email);
	
	List<Account> findAll();

	void saveAccount(Account account);
	
}
