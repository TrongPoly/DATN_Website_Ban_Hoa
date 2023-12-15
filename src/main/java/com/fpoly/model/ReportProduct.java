package com.fpoly.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReportProduct implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	String tenSanPham;
	long soLuongBan;
	BigDecimal tongTien;


	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	
	public long getSoLuongBan() {
		return soLuongBan;
	}

	public void setSoLuongBan(long soLuongBan) {
		this.soLuongBan = soLuongBan;
	}

	public BigDecimal getTongTien() {
		return tongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}

	public ReportProduct(String tenSanPham, long soLuongBan, BigDecimal tongTien) {
		super();
		this.tenSanPham = tenSanPham;
		this.soLuongBan = soLuongBan;
		this.tongTien = tongTien;
	}

	public ReportProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

}
