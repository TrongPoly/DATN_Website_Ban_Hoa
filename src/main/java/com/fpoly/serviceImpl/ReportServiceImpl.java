package com.fpoly.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.fpoly.model.ReportCost;
import com.fpoly.model.ReportProduct;
import com.fpoly.service.*;
import com.fpoly.repository.*;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportCostRepo reportCostRepo;
	@Autowired
	ReportProductRepository reportProductRepository;

	@Override
	public List<ReportCost> generateReport(int month, int year) {
		return reportCostRepo.reportCost(month,year);
	}

	@Override
	public List<ReportProduct> getReportProduct(int month, int year) {
		return reportProductRepository.reportProduct(month,year);
	}
}
