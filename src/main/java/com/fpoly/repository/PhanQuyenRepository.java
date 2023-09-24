package com.fpoly.repository;

import com.fpoly.model.PhanQuyen;
import com.fpoly.model.PhanQuyenId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, PhanQuyenId> {
}