package com.example.demo.service;

import com.example.demo.entity.Order;

public interface IOrderService {
	
	public Order placeOrder(Long customerId, Long productId, int quantity);

}
