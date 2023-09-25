package com.fpoly.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "don_hang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_don_hang", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_dat_hang")
    private NguoiDung nguoiDatHang;

    @Column(name = "ngay_dat")
    private Instant ngayDat;

    @Column(name = "ngay_lay")
    private Instant ngayLay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NguoiDung getNguoiDatHang() {
        return nguoiDatHang;
    }

    public void setNguoiDatHang(NguoiDung nguoiDatHang) {
        this.nguoiDatHang = nguoiDatHang;
    }

    public Instant getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Instant ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Instant getNgayLay() {
        return ngayLay;
    }

    public void setNgayLay(Instant ngayLay) {
        this.ngayLay = ngayLay;
    }

}