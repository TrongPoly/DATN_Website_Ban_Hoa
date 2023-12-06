package com.fpoly.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import com.fpoly.model.ReportCost;

public interface ReportCostRepo extends JpaRepository<ReportCost, Long> {
	  @Procedure(name = "sp_ReportCost")
	    List<ReportCost> spReportCost(@Param("month") Integer month);
}
