package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Account;
import com.fpoly.repository.AccountRepository;
import com.fpoly.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	@Override
	public Account findByid(String email) {
		return accountRepository.findById(email).orElse(null);
	}
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

}
