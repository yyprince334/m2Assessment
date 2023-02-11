package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerException;
import com.example.demo.service.ICustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CustomerController {
	
	private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
    
    @GetMapping("/viewAllCustomer")
    public List<Customer> getAllCustomers() throws CustomerException {
        return customerService.getAllCustomers();
    }


    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id) throws CustomerException {
        return customerService.getCustomerById(id);
    }

}
