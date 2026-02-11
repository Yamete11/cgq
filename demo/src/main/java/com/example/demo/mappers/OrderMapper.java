package com.example.demo.mappers;

import com.example.demo.DTOs.OrderDTO;
import com.example.demo.DTOs.OrderItemDTO;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        List<OrderItemDTO> items = order.getItems().stream()
                .map(OrderMapper::toItemDTO)
                .collect(Collectors.toList());

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setItems(items);
        dto.setTotalPrice(order.getTotalPrice());
        dto.setCreatedAt(order.getCreatedAt());
        return dto;
    }

    private static OrderItemDTO toItemDTO(OrderItem item) {
        return new OrderItemDTO(
                item.getProduct().getId(),
                item.getProduct().getTitle(),
                item.getPrice(),
                item.getQuantity()
        );
    }
}
