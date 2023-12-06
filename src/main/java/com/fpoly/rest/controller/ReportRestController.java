package com.fpoly.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Order;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.ReportCost;
import com.fpoly.service.OrderDetailsService;
import com.fpoly.service.OrderService;
import com.fpoly.service.ReportService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/rest/report")
public class ReportRestController {
	@Autowired OrderService orderService;
	@Autowired ReportService reportService;
	@Autowired OrderDetailsService orderDetailsService;
	
	public Integer monthCurrent() {
		Date date = new Date();
		return date.getMonth()+1;
	}
	
	@GetMapping("/total")
	public double total(@RequestParam("month") int month) {
		
		List<Order> orders = orderService.findOrderInMonth(month);
		double totalCost = 0.0;
		for(Order order : orders ) {
			List<OrderDetail> orderDetail = orderDetailsService.findByOrder(order);
			for(OrderDetail od : orderDetail) {
				totalCost += od.getPrice().doubleValue() *  od.getQuantity() ;
			}
		}
		return totalCost;
	}
	@GetMapping("/reportcost")
	public List<ReportCost> reportCostInMonth(@RequestParam("month") int month){
		List<ReportCost> lst = reportService.generateReport(month);
		return lst;
	}
	@GetMapping("/reportcost/compare")
	public List<ReportCost> compare(@RequestParam("month") int month){
		List<ReportCost> lst1 = reportService.generateReport(month);
		return lst1;
	}
	
}
