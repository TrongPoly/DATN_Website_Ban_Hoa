package com.fpoly.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Table;
@Entity
@Table(name = "sp_ReportCost")
public class ReportCost {
	 @Id
	    @Column(name = "NgayBan")
	    private String ngayBan;

	    @Column(name = "TongHoaDon")
	    private Long tongHoaDon;

	    @Column(name = "DoanhThu")
	    private Double doanhThu;

	    public String getNgayBan() {
	        return ngayBan;
	    }

	    public void setNgayBan(String ngayBan) {
	        this.ngayBan = ngayBan;
	    }

	    public Long getTongHoaDon() {
	        return tongHoaDon;
	    }

	    public void setTongHoaDon(Long tongHoaDon) {
	        this.tongHoaDon = tongHoaDon;
	    }

	    public Double getDoanhThu() {
	        return doanhThu;
	    }

	    public void setDoanhThu(Double doanhThu) {
	        this.doanhThu = doanhThu;
	    }
}
