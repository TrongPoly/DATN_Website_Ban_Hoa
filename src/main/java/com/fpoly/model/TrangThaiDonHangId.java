package com.fpoly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TrangThaiDonHangId implements Serializable {
    private static final long serialVersionUID = 7553130150889064091L;
    @NotNull
    @Column(name = "ma_don_hang", nullable = false)
    private UUID maDonHang;

    @NotNull
    @Column(name = "ma_trang_thai", nullable = false)
    private Integer maTrangThai;

    public UUID getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(UUID maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Integer getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(Integer maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TrangThaiDonHangId entity = (TrangThaiDonHangId) o;
        return Objects.equals(this.maDonHang, entity.maDonHang) &&
                Objects.equals(this.maTrangThai, entity.maTrangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDonHang, maTrangThai);
    }

}