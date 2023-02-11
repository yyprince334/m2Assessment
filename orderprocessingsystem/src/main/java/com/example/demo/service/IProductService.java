package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface IProductService {
	
	public List<Product> getAllProducts();
	public Product getProductById(Long id);
	public Product addProduct(Product product);

}
