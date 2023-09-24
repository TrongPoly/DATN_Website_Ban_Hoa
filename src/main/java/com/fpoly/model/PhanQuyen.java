package com.fpoly.model;

import jakarta.persistence.*;

@Entity
@Table(name = "phan_quyen")
public class PhanQuyen {
    @EmbeddedId
    private PhanQuyenId id;

    @MapsId("idQuyen")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_quyen", nullable = false)
    private Quyen idQuyen;

    @MapsId("idNguoiDung")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private NguoiDung idNguoiDung;

    public PhanQuyenId getId() {
        return id;
    }

    public void setId(PhanQuyenId id) {
        this.id = id;
    }

    public Quyen getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(Quyen idQuyen) {
        this.idQuyen = idQuyen;
    }

    public NguoiDung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(NguoiDung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

}