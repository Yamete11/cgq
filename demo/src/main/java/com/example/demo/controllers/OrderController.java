package com.example.demo.controllers;

import com.example.demo.DTOs.OrderDTO;
import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDTO> getOrdersByUser(@RequestParam Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @PostMapping
    public OrderDTO createOrder(@RequestParam Long userId, @RequestBody List<Long> productIds) {
        return orderService.createOrder(userId, productIds);
    }
}
