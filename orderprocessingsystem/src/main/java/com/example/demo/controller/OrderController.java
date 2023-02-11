package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.service.IOrderService;

@RestController
public class OrderController {
	
	 private final IOrderService orderService;

	    @Autowired
	    public OrderController(IOrderService orderService) {
	        this.orderService = orderService;
	    }

	    @PostMapping("/orders")
	    public Order placeOrder(@RequestParam Long customerId, @RequestParam Long productId, @RequestParam int quantity) {
	        return orderService.placeOrder(customerId, productId, quantity);
	    }

}
