package com.order.management.controller;

import com.order.management.dto.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private List<Order> orders = new ArrayList<>(Arrays.asList(
            new Order(1L, "Alice", "Pending"),
            new Order(2L, "Bob", "Shipped"),
            new Order(3L, "Charlie", "Delivered")
    ));

    private long nextId = 4;

    @GetMapping
    public List<Order> getAllOrders() {
        return orders;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order newOrder) {
        newOrder.setId(nextId++);
        orders.add(newOrder);
        return newOrder;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean removed = orders.removeIf(order -> order.getId().equals(id));
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

}
