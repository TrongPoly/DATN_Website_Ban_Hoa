package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Role;
import com.fpoly.service.RoleService;

@RestController
@RequestMapping("/rest")
public class AdminRoleRestController {
	@Autowired
	RoleService RoleService;
	
	
	@GetMapping("/role")
	public List<Role> getAll (Model model){
		return RoleService.findAllRole();
	}
}
