package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.SanPham;
import com.fpoly.service.SanPhamService;

@RestController
@CrossOrigin("*")
@RequestMapping("/sanPham")
public class SanPhamRestController {
	@Autowired
	SanPhamService sanPhamService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllSanPham (){
		List<SanPham> listSanPham = sanPhamService.findAllSanPham();
		return ResponseEntity.ok(listSanPham);
	}
}
