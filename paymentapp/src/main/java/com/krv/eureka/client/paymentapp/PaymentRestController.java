package com.krv.eureka.client.paymentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentRestController {

	@Autowired
	private CartRestConsumer consumer; // HAS-A

	@GetMapping("/data")
	public String getPaymentData() {
		return "FROM PAYMENT-SERVICE : " + consumer.getCartInfo();
	}
}