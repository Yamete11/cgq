package com.example.demo.services;

import com.example.demo.DTOs.ProductDTO;
import com.example.demo.entities.Product;
import com.example.demo.mappers.ProductMapper;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.toDTOList(products);
    }

    public Optional<ProductDTO> getById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDTO);
    }

    public Optional<Product> getEntityById(Long id) {
        return productRepository.findById(id);
    }

}
