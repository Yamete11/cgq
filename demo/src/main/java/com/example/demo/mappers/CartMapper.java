package com.example.demo.mappers;

import com.example.demo.DTOs.CartDTO;
import com.example.demo.DTOs.CartProductDTO;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        List<CartProductDTO> products = cart.getProducts().stream()
                .map(CartMapper::toProductDTO)
                .collect(Collectors.toList());

        BigDecimal totalPrice = products.stream()
                .map(CartProductDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setProducts(products);
        dto.setTotalPrice(totalPrice);

        return dto;
    }

    private static CartProductDTO toProductDTO(Product product) {
        CartProductDTO dto = new CartProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
