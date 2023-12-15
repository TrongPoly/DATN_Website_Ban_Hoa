package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/revenue")
public class ReportController {

	@GetMapping("/report_product")
	public String getReportProduct() {
		return "admin/ProductReport";
	}
	
	@GetMapping("/report")
	public String getDashboard() {
		return "admin/index";
	}
}
