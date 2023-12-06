package com.fpoly.service;

import java.util.List;

import com.fpoly.model.ReportCost;

public  interface ReportService {
	
	List<ReportCost> generateReport(int month);
}
