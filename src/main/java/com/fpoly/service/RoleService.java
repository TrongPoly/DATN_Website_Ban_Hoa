package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Role;

public interface RoleService {
	List<Role> findAllRole();

	Role findById(int id);
	
}
