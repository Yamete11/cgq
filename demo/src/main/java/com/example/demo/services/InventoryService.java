package com.example.demo.services;

import com.example.demo.entities.Inventory;
import com.example.demo.entities.Product;
import com.example.demo.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Optional<Inventory> getByProduct(Product product) {
        return inventoryRepository.findByProduct(product);
    }

    public Inventory setQuantity(Product product, int quantity) {
        Inventory inventory = inventoryRepository.findByProduct(product)
                .orElse(new Inventory());
        inventory.setProduct(product);
        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }

    public void decrease(Product product, int amount) {
        Inventory inventory = inventoryRepository.findByProduct(product)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product"));
        if (inventory.getQuantity() < amount) {
            throw new RuntimeException("Not enough stock for product: " + product.getTitle());
        }
        inventory.setQuantity(inventory.getQuantity() - amount);
        inventoryRepository.save(inventory);
    }
}
