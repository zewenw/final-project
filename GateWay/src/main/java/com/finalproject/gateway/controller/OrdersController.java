package com.finalproject.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class OrdersController {
	
	@GetMapping("/orders")
	public List<OrderRest> getOrders() {
		
		OrderRest order1 = new OrderRest(UUID.randomUUID().toString(),
				"product-id-1", "user-id-1", 1, OrderStatus.NEW);
		
		OrderRest order2 = new OrderRest(UUID.randomUUID().toString(),
				"product-id-2", "user-id-1", 1, OrderStatus.NEW);
		
		return Arrays.asList(order1, order2);
	}

}
