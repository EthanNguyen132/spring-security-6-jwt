package com.ethan.springsecurity.jwtdemo.controller;

import com.ethan.springsecurity.jwtdemo.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@GetMapping("products/{id}")
	public Product getProductById(@PathVariable int id) {
		return Product.builder().id(id).name("Apple").build();
	}
}
