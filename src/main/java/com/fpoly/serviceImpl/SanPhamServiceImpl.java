package com.fpoly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.SanPham;
import com.fpoly.repository.SanPhamRepository;
import com.fpoly.service.SanPhamService;

@Service
public class SanPhamServiceImpl implements SanPhamService{
	@Autowired
	SanPhamRepository sanPhamRepository;
	
	@Override
	public List<SanPham> findAllSanPham() {
		return sanPhamRepository.findAll();
	}

	@Override
	public SanPham findOneSanPham(Integer idProduct) {
		return sanPhamRepository.findById(idProduct).orElse(null);
	}

}
