package com.fpoly.rest.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
import com.fpoly.model.Account;
import com.fpoly.model.Order;
import com.fpoly.model.OrderDetail;
import com.fpoly.model.OrderStatus;
import com.fpoly.model.OrderStatusHistory;
import com.fpoly.model.Product;
import com.fpoly.service.AccountService;
import com.fpoly.service.OrderDetailsService;
import com.fpoly.service.OrderService;
import com.fpoly.service.OrderStatusHistoryService;
import com.fpoly.service.OrderStatusService;
import com.fpoly.service.ProductService;
import com.fpoly.service.SessionService;

@RestController
@RequestMapping("/api")
public class OrderRestController {
	@Autowired
	OrderDetailsService orderDetailsService;
	@Autowired
	OrderService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	AccountService accountService;
	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	OrderStatusHistoryService orderStatusHistoryService;
	@Autowired
	SessionService sessionService;

	@GetMapping("/order/get_all")
	public ResponseEntity<List<Order>> getAll() {
		List<Order> listOrders = orderService.findAllOrder();
		return ResponseEntity.ok(listOrders);
	}

	@GetMapping("/order/id")
	public ResponseEntity<Order> getOneOrder(@RequestParam("idOrder") int idOrder) {
		Order order = orderService.findById(idOrder);
		return ResponseEntity.ok(order);
	}
	@GetMapping("/order/search")
	public ResponseEntity<List<Order>> searchOrder(@RequestParam("key") int key) {
		List<Order> order = orderService.searchById(key);
		return ResponseEntity.ok(order);
	}
	@GetMapping("/order/search_by_email")
	public ResponseEntity<List<Order>> searchOrderByEmail(@RequestParam("email") String key) {
		List<Order> order = orderService.searchByEmail(key);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/order/get_all/{email}")
	public ResponseEntity<List<Order>> getAllByEmail(@PathVariable("email") String email) {
		List<Order> listOrders = orderService.findByCustomer(accountService.findByid(email));
		return ResponseEntity.ok(listOrders);
	}

	@PostMapping("/order/save/{email}")
	public ResponseEntity<List<OrderDetail>> saveOrder(@RequestBody List<OrderDTO> orderDTO,
			@PathVariable("email") String emailCustomer, @RequestParam("pickUpDate") String pickUpDate,
			@RequestParam("methodPayment") Integer methodPayment,
			@RequestParam("billingFullName") String billingFullName,
			@RequestParam("billingPhoneNumber") String billingPhoneNumber, @RequestParam("note") String note,
			@RequestParam("shippingFullName") String shippingFullName,
			@RequestParam("shippingPhoneNumber") String shippingPhoneNumber) {
		Order order = new Order();

		Account account = accountService.findByid(emailCustomer);

		account.setPhoneNumber(billingPhoneNumber);
		account.setFullName(billingFullName);
		accountService.saveAccount(account);

		order.setEmail(account);
		order.setBillingFullName(billingFullName);
		order.setBillingPhoneNumber(billingPhoneNumber);
		order.setShippingFullName(shippingFullName);
		order.setShippingPhoneNumber(shippingPhoneNumber);
		order.setNote(note);
		order.setChecked(false);
		Instant od = Instant.now();
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(pickUpDate, inputFormatter);
		Instant pu = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		String formattedPickUpDate = pu.toString(); // Định dạng lại thành chuỗi "2023-10-18 02:49:57.923"

		OrderStatus orderStatus = orderStatusService.findById(0);

		Instant pickUD = Instant.parse(formattedPickUpDate);
		order.setOrderDate(od);
		order.setPickUpDate(pickUD);
		order.setStatus(orderStatus);
		order.setMethodPayment(methodPayment);
		orderService.saveOrder(order);
		// Save status
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		orderStatusHistory.setOrder(order);
		orderStatusHistory.setChangeDate(od);
		orderStatusHistory.setEmail(account);
		orderStatusHistory.setStatus(orderStatus);
		orderStatusHistoryService.saveOrderStatusHistory(orderStatusHistory);

		List<OrderDetail> orderDetails = new ArrayList<>();
		for (int i = 0; i < orderDTO.size(); i++) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			Product product = productService.findById(orderDTO.get(i).getId());
			orderDetail.setProduct(product);
			orderDetail.setPrice(product.getPrice());
			orderDetail.setQuantity(orderDTO.get(i).getQuant());
			orderDetails.add(orderDetail);
		}
		orderDetailsService.saveAll(orderDetails);
		return ResponseEntity.ok(orderDetails);
	}

	@GetMapping("/order/{emailUser}")
	public ResponseEntity<List<Order>> getOrder(@PathVariable("emailUser") String email,
			@RequestParam("status") int status) {
//		Customer customer = customerService.findByEmail(accountService.findByid(email));
		Account account = accountService.findByid(email);
		List<Order> listOrder = orderService.findByCustomer(account);
		return ResponseEntity.ok(listOrder.stream().filter(od -> od.getStatus().getStatusId() == status)
				.sorted(Comparator.comparing(Order::getId).reversed()).toList());
	}

	@GetMapping("/order/details/{idOrder}")
	public ResponseEntity<List<OrderDetail>> getOrderDetails(@PathVariable("idOrder") int idOrder) {
		Order order = orderService.findById(idOrder);
		if(sessionService.getSession("user").getRole().getId()!=2) {
		order.setChecked(true);
		orderService.saveOrder(order);
		}
		List<OrderDetail> listOrderDetails = orderDetailsService.findByOrder(order);
		return ResponseEntity.ok(listOrderDetails);
	}

	@PutMapping("/order/update_status/{idOrder}")
	public ResponseEntity<Order> requestCancel(@PathVariable("idOrder") Integer idOrder,
			@RequestParam("statusId") Integer statusId, @RequestParam("note") Optional<String> note,
			@RequestParam("email") String email) {
		Order order = orderService.findById(idOrder);
		OrderStatus orderStatus = orderStatusService.findById(statusId);
		order.setStatus(orderStatus);
		order.setChecked(false);
		orderService.saveOrder(order);
		// set status
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		orderStatusHistory.setOrder(order);
		orderStatusHistory.setStatus(orderStatus);
		orderStatusHistory.setNote(note.orElse(""));
		Instant changeDate = Instant.now();
		orderStatusHistory.setChangeDate(changeDate);
		orderStatusHistory.setEmail(accountService.findByid(email));
		orderStatusHistoryService.saveOrderStatusHistory(orderStatusHistory);

		// status= 4 (Hủy đơn)
		if (statusId == 4) {
			// reset số lượng sản phẩm
			List<OrderDetail> orderDetail = orderDetailsService.findByOrder(order);
			for (OrderDetail ordDtail : orderDetail) {
				Product product = productService.findById(ordDtail.getProduct().getId());
				product.setQuantity(product.getQuantity() + ordDtail.getQuantity());
				product.setIsAvailable(true);
				productService.saveProduct(product);
			}
		}
		return ResponseEntity.ok(order);
	}

	// getstatus
	@GetMapping("/order/status/{idOrder}")
	public ResponseEntity<List<OrderStatusHistory>> getStatus(@PathVariable("idOrder") int idOrder) {
		List<OrderStatusHistory> listStatus = orderStatusHistoryService.findByOrder(orderService.findById(idOrder));
		return ResponseEntity.ok(listStatus);
	}

//Admin
	@GetMapping("/order")
	public ResponseEntity<List<Order>> getAllOrderByStatus(@RequestParam("status") int status) {
		List<Order> listOrder = orderService.findByStatus(orderStatusService.findById(status));
		return ResponseEntity.ok(listOrder);
	}
}
