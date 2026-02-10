package com.example.demo.config;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.entities.Inventory;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final CartRepository cartRepository;

    @Autowired
    public DataInitializer(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           InventoryRepository inventoryRepository,
                           CartRepository cartRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category electronics = new Category();
        electronics.setTitle("Electronics");
        categoryRepository.save(electronics);

        Category books = new Category();
        books.setTitle("Books");
        categoryRepository.save(books);

        Category clothing = new Category();
        clothing.setTitle("Clothing");
        categoryRepository.save(clothing);

        Category home = new Category();
        home.setTitle("Home & Kitchen");
        categoryRepository.save(home);

        saveProductWithInventory("Smartphone", BigDecimal.valueOf(120.00), electronics, 50);
        saveProductWithInventory("Laptop", BigDecimal.valueOf(175.00), electronics, 30);
        saveProductWithInventory("Headphones", BigDecimal.valueOf(20.00), electronics, 100);

        saveProductWithInventory("Novel", BigDecimal.valueOf(45.00), books, 80);
        saveProductWithInventory("Cookbook", BigDecimal.valueOf(80.00), books, 60);
        saveProductWithInventory("Science Fiction", BigDecimal.valueOf(101.00), books, 40);

        saveProductWithInventory("T-shirt", BigDecimal.valueOf(95.00), clothing, 200);
        saveProductWithInventory("Jeans", BigDecimal.valueOf(37.00), clothing, 150);
        saveProductWithInventory("Jacket", BigDecimal.valueOf(25.00), clothing, 70);

        saveProductWithInventory("Coffee Maker", BigDecimal.valueOf(77.00), home, 40);
        saveProductWithInventory("Blender", BigDecimal.valueOf(23.00), home, 60);
        saveProductWithInventory("Vacuum Cleaner", BigDecimal.valueOf(44.00), home, 25);

        if (cartRepository.count() == 0) {
            Cart cart = new Cart();
            cartRepository.save(cart);
        }
    }

    private void saveProductWithInventory(String title, BigDecimal price, Category category, int quantity) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setCategory(category);
        productRepository.save(product);

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }
}
