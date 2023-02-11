package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class ICustomerServiceImpl implements ICustomerService{
	 @Autowired
	    private CustomerRepository customerRepository;
	    
	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }
	    
	    public Customer getCustomerById(Long id) {
	        return customerRepository.findById(id).orElse(null);
	    }

	    public Customer addCustomer(Customer customer) {
	        return customerRepository.save(customer);
	    }
}
