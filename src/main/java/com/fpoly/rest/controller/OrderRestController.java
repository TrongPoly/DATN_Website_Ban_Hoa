package com.fpoly.rest.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.DTO.OrderDTO;
import com.fpoly.model.Customer;
import com.fpoly.model.Order;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.Product;
import com.fpoly.service.AccountService;
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
	@Autowired
	AccountService accountService;

	@PostMapping("/order/save/{email}")
	public ResponseEntity<List<OrderDetail>> saveOrder(@RequestBody List<OrderDTO> orderDTO,
			@PathVariable("email") String emailCustomer, @RequestParam("pickUpDate") String pickUpDate) {
		Order order = new Order();
		Customer customer = customerService.findByEmail(accountService.findByid(emailCustomer));
		order.setCustomer(customer);
		Instant od = Instant.now();
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(pickUpDate, inputFormatter);
		Instant pu = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		String formattedPickUpDate = pu.toString(); // Định dạng lại thành chuỗi "2023-10-18 02:49:57.923"
		
		
		Instant pickUD = Instant.parse(formattedPickUpDate);
		order.setOrderDate(od);
		order.setPickUpDate(pickUD);
		order.setStatus("0");
		orderService.saveOrder(order);

		List<OrderDetail> orderDetails = new ArrayList<>();
		for (int i = 0; i < orderDTO.size(); i++) {
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
	
	@GetMapping("/order/{emailUser}")
	public ResponseEntity<List<Order>> getOrder(@PathVariable("emailUser") String email,
			@RequestParam("status") String status) {
		Customer customer = customerService.findByEmail(accountService.findByid(email));
		List<Order> listOrder = orderService.findByCustomer(customer);
		return ResponseEntity.ok(listOrder.stream().filter(od -> od.getStatus().equals(status)).sorted(Comparator.comparing(Order:: getId).reversed()).toList());
	}
	@GetMapping("/order/details/{idOrder}")
	public ResponseEntity<List<OrderDetail>> getOrderDetails(@PathVariable("idOrder") int idOrder) {
			List<OrderDetail> listOrderDetails = orderDetailsService.findByOrder(orderService.findById(idOrder));
			return ResponseEntity.ok(listOrderDetails);
	}
	@PutMapping("/order/cancel_reuquest/{idOrder}")
	public ResponseEntity<Order> requestCancel(@PathVariable("idOrder") Integer idOrder){
		Order order = orderService.findById(idOrder);
		order.setStatus("5");
		orderService.saveOrder(order);
		return ResponseEntity.ok(order);
	}
}
