package com.fpoly.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "don_hang_chi_tiet")
public class DonHangChiTiet {
    @Id
    @Column(name = "id_don_hang_chi_tiet", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_don_hang")
    private DonHang idDonHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private SanPham idSanPham;

    @Column(name = "so_luong_san_pham")
    private Integer soLuongSanPham;

    @Column(name = "don_gia", precision = 19, scale = 4)
    private BigDecimal donGia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DonHang getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(DonHang idDonHang) {
        this.idDonHang = idDonHang;
    }

    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Integer getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(Integer soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

}