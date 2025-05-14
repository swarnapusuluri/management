package com.order.management.controller;

import com.order.management.entity.Product;
import com.order.management.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // For testing
    @PostConstruct
    public void init() {
        productRepository.save(new Product(null, "Laptop", "Gaming laptop", 1200.00, 10));
        productRepository.save(new Product(null, "Phone", "Smartphone", 800.00, 15));
    }
}

