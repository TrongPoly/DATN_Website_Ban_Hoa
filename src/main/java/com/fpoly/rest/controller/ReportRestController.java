package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.Order;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.OrderStatus;
import com.fpoly.model.ReportCost;
import com.fpoly.model.ReportProduct;
import com.fpoly.service.OrderDetailsService;
import com.fpoly.service.OrderService;
import com.fpoly.service.OrderStatusService;
import com.fpoly.service.ReportService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/rest/report")
public class ReportRestController {
	@Autowired OrderService orderService;
	@Autowired ReportService reportService;
	@Autowired OrderDetailsService orderDetailsService;
	@Autowired OrderStatusService orderStatusService;
	
	@GetMapping("/total")
	public double total(@RequestParam("month") int month,@RequestParam("year") int year) {
		
		List<Order> orders = orderService.findOrderInMonth(month,year);
		double totalCost = 0.0;
		OrderStatus orderStatus = orderStatusService.findById(3);
		for(Order order : orders ) {
			List<OrderDetail> orderDetail = orderDetailsService.findAllByOrderStatus(order, orderStatus);
			for(OrderDetail od : orderDetail) {
				totalCost += od.getPrice().doubleValue() *  od.getQuantity() ;
			}
		}
		return totalCost;
	}
	@GetMapping("/reportcost")
	public List<ReportCost> reportCostInMonth(@RequestParam("month") int month,@RequestParam("year") int year){
		List<ReportCost> lst = reportService.generateReport(month,year);
		return lst;
	}
	@GetMapping("/product")
	public List<ReportProduct> reportProduct(@RequestParam("month") int month,@RequestParam("year") int year){
		List<ReportProduct> lst = reportService.getReportProduct(month,year);
		return lst;
	}
	
	@GetMapping("/data/year")
	public List<Integer> getDataYear(){
		
		return orderService.getYearOrder();
	}
	
	
}
