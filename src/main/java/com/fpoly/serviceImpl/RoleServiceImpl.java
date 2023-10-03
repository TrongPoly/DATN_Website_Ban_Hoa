package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Role;
import com.fpoly.repository.RoleRepository;
import com.fpoly.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

}
