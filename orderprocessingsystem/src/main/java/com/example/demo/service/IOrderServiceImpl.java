package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;

@Service
public class IOrderServiceImpl implements IOrderService {
	
	@Autowired
    private ICustomerService customerService;
    @Autowired
    private IProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    
    // method to place an order
    public Order placeOrder(Long customerId, Long productId, int quantity) {
        Customer customer = customerService.getCustomerById(customerId);
        Product product = productService.getProductById(productId);
        
        if (customer == null) {
            throw new IllegalArgumentException("Customer with id " + customerId + " not found");
        }
        
        if (product == null) {
            throw new IllegalArgumentException("Product with id " + productId + " not found");
        }
        
        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setOrderAmount(quantity * product.getPrice());
        
        return orderRepository.save(order);

    }
}
