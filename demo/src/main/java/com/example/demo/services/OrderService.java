package com.example.demo.services;

import com.example.demo.DTOs.OrderDTO;
import com.example.demo.entities.*;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;
    private final ProductService productService;

    public OrderDTO createOrder(Long userId, List<Long> productIds) {
        Order order = new Order();
        order.setUserId(userId);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Long productId : productIds) {
            Product product = productService.getEntityById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (!inventoryService.isAvailable(product, 1)) {
                throw new RuntimeException("Product " + product.getTitle() + " is out of stock");
            }

            inventoryService.decrease(product, 1);

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(1);
            item.setPrice(product.getPrice());
            items.add(item);

            total = total.add(product.getPrice());
        }

        order.setItems(items);
        order.setTotalPrice(total);

        Order saved = orderRepository.save(order);
        return OrderMapper.toDTO(saved);
    }

    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(OrderMapper::toDTO)
                .toList();
    }
}
