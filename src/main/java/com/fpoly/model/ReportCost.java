package com.fpoly.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Table;

@Entity
public class ReportCost implements java.io.Serializable{
	@Id
	private Instant ngayBan;

	private Long tongHoaDon;

	private BigDecimal doanhThu;

	public Instant getNgayBan() {
		return ngayBan;
	}

	public void setNgayBan(Instant ngayBan) {
		this.ngayBan = ngayBan;
	}

	public Long getTongHoaDon() {
		return tongHoaDon;
	}

	public void setTongHoaDon(Long tongHoaDon) {
		this.tongHoaDon = tongHoaDon;
	}

	public BigDecimal getDoanhThu() {
		return doanhThu;
	}

	public void setDoanhThu(BigDecimal doanhThu) {
		this.doanhThu = doanhThu;
	}

	public ReportCost(Instant ngayBan, Long tongHoaDon, BigDecimal doanhThu) {
		super();
		this.ngayBan = ngayBan;
		this.tongHoaDon = tongHoaDon;
		this.doanhThu = doanhThu;
	}

	public ReportCost() {
		super();
	}

}
