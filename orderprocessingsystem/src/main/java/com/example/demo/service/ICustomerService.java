package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Customer;

public interface ICustomerService {
	
	public List<Customer> getAllCustomers();
	public Customer getCustomerById(Long id);
	public Customer addCustomer(Customer customer);

}
