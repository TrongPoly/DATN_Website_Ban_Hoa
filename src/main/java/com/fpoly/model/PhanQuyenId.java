package com.fpoly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PhanQuyenId implements Serializable {
    private static final long serialVersionUID = -1377353222765162660L;
    @NotNull
    @Column(name = "id_quyen", nullable = false)
    private Integer idQuyen;

    @NotNull
    @Column(name = "id_nguoi_dung", nullable = false)
    private Integer idNguoiDung;

    public Integer getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(Integer idQuyen) {
        this.idQuyen = idQuyen;
    }

    public Integer getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(Integer idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PhanQuyenId entity = (PhanQuyenId) o;
        return Objects.equals(this.idQuyen, entity.idQuyen) &&
                Objects.equals(this.idNguoiDung, entity.idNguoiDung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuyen, idNguoiDung);
    }

}