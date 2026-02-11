package com.example.demo.services;

import com.example.demo.DTOs.CartDTO;
import com.example.demo.DTOs.ProductDTO;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;
import com.example.demo.mappers.CartMapper;
import com.example.demo.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartDTO addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productService.getEntityById(productId) // <-- entity, а не DTO
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.getProducts().add(product);
        Cart savedCart = cartRepository.save(cart);
        return CartMapper.toDTO(savedCart);
    }


    public CartDTO removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productService.getEntityById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.getProducts().remove(product);
        Cart savedCart = cartRepository.save(cart);
        return CartMapper.toDTO(savedCart);
    }

    public Optional<CartDTO> getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .map(CartMapper::toDTO);
    }
}
