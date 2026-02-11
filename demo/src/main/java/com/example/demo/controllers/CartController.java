package com.example.demo.controllers;

import com.example.demo.DTOs.CartDTO;
import com.example.demo.entities.Cart;
import com.example.demo.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long id) {
        return cartService.getCart(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<CartDTO> addProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.addProductToCart(cartId, productId));
    }

    @PostMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<CartDTO> removeProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeProductFromCart(cartId, productId));
    }
}
