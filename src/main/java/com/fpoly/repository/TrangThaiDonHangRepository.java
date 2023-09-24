package com.fpoly.repository;

import com.fpoly.model.TrangThaiDonHang;
import com.fpoly.model.TrangThaiDonHangId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrangThaiDonHangRepository extends JpaRepository<TrangThaiDonHang, TrangThaiDonHangId> {
}