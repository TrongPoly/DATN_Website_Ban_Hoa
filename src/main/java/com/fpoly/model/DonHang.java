package com.fpoly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "don_hang")
public class DonHang {
    @Id
    @Column(name = "id_don_hang", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ngay_dat")
    private Instant ngayDat;

    @Column(name = "ngay_lay")
    private Instant ngayLay;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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