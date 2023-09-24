package com.fpoly.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "trang_thai_don_hang")
public class TrangThaiDonHang {
    @EmbeddedId
    private TrangThaiDonHangId id;

    @MapsId("maDonHang")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_don_hang", nullable = false)
    private DonHang maDonHang;

    @MapsId("maTrangThai")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_trang_thai", nullable = false)
    private TrangThai maTrangThai;

    @Column(name = "ngay_cap_nhap")
    private Instant ngayCapNhap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_cap_nhat")
    private NguoiDung nguoiCapNhat;

    public TrangThaiDonHangId getId() {
        return id;
    }

    public void setId(TrangThaiDonHangId id) {
        this.id = id;
    }

    public DonHang getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(DonHang maDonHang) {
        this.maDonHang = maDonHang;
    }

    public TrangThai getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(TrangThai maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    public Instant getNgayCapNhap() {
        return ngayCapNhap;
    }

    public void setNgayCapNhap(Instant ngayCapNhap) {
        this.ngayCapNhap = ngayCapNhap;
    }

    public NguoiDung getNguoiCapNhat() {
        return nguoiCapNhat;
    }

    public void setNguoiCapNhat(NguoiDung nguoiCapNhat) {
        this.nguoiCapNhat = nguoiCapNhat;
    }

}