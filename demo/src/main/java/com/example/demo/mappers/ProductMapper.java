package com.example.demo.mappers;


import com.example.demo.DTOs.ProductDTO;
import com.example.demo.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory().getTitle());
        return productDTO;
    }

    public static List<ProductDTO> toDTOList(List<Product> entities) {
        return entities.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }
}
