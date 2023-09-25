package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/{idProduct}")
	public ResponseEntity<?> getOneSanPham(@PathVariable("idProduct") Integer idProduct){
		SanPham sanPham = sanPhamService.findOneSanPham(idProduct);
		if (sanPham!=null) {
			return ResponseEntity.ok(sanPham);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sản phẩm");
	}
}
