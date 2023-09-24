package com.fpoly.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gio_hang_chi_tiet")
public class GioHangChiTiet {
    @Id
    @Column(name = "id_gio_hang_chi_tiet", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gio_hang")
    private GioHang idGioHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private SanPham idSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "da_chon")
    private Boolean daChon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GioHang getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(GioHang idGioHang) {
        this.idGioHang = idGioHang;
    }

    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Boolean getDaChon() {
        return daChon;
    }

    public void setDaChon(Boolean daChon) {
        this.daChon = daChon;
    }

}