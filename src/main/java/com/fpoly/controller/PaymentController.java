package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {
	@GetMapping("payment_status")
	public String paySuccess(@RequestParam("vnp_TransactionStatus") String status) {
		if (!status.equals("00")) {
			return "redirect:/index";
		}
		return "orderSuccess";
	}
}
