package com.fpoly.rest.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.DTO.OrderDTO;
import com.fpoly.model.Customer;
import com.fpoly.model.Order;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.Product;
import com.fpoly.service.CustomerService;
import com.fpoly.service.OrderDetailsService;
import com.fpoly.service.OrderService;
import com.fpoly.service.ProductService;

@RestController
@RequestMapping("/api")
public class OrderRestController {
	@Autowired
	OrderDetailsService orderDetailsService;
	@Autowired
	OrderService orderService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;
	
	@PostMapping("/order/save")
	public ResponseEntity<List<OrderDetail>> saveOrder(@RequestBody List<OrderDTO> orderDTO){
		Order order = new Order();
		Customer customer = customerService.findById(2);
		order.setCustomer(customer);
		Instant od = Instant.now();
		order.setOrderDate(od);
		order.setPickUpDate(od);
		order.setStatus("Chờ xử lý");
		orderService.saveOrder(order);
		
		 List<OrderDetail> orderDetails = new ArrayList<>();
		    for (int i = 0; i<orderDTO.size(); i++) {
		        OrderDetail orderDetail = new OrderDetail();
		        orderDetail.setOrder(order);
		        Product product = productService.findById(orderDTO.get(i).getId());
		        orderDetail.setProduct(product);
		        orderDetail.setQuantity(orderDTO.get(i).getQuant());
		        orderDetails.add(orderDetail);
		    }
		    orderDetailsService.saveAll(orderDetails);
		return ResponseEntity.ok(orderDetails);
	}
}
