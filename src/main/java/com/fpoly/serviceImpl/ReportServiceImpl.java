package com.fpoly.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.fpoly.model.ReportCost;
import com.fpoly.service.*;
import com.fpoly.repository.*;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportCostRepo reportCostRepo;

	@Override
	public List<ReportCost> generateReport(int month) {
		return reportCostRepo.reportCost(month);
	}
}
