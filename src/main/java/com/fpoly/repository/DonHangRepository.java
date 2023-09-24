package com.fpoly.repository;

import com.fpoly.model.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DonHangRepository extends JpaRepository<DonHang, UUID> {
}