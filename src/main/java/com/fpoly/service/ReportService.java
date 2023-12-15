package com.fpoly.service;

import java.util.List;

import com.fpoly.model.ReportCost;
import com.fpoly.model.ReportProduct;

public  interface ReportService {
	
		List<ReportCost> generateReport(int month, int year);

		List<ReportProduct> getReportProduct(int month, int year);
}
