package com.fpoly.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	
	@Query(name = "findByFullName")
	List<Account> findByFullName(String tenKhachHang,Pageable page);
	List<Account> findByFullNameContaining(String keyword);

}
