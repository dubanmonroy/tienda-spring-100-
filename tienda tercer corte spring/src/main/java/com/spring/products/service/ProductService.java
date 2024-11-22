package com.spring.products.service;

import com.spring.products.entity.ProductEntity;
import com.spring.products.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        createInitialProducts();
    }

    private void createInitialProducts() {
        if (productRepository.count() == 0) {
            Arrays.asList(
                    new ProductEntity("Monitor", "Visual", 700.000, 50),
                    new ProductEntity("TarjetaGrafica", "Rendimiento", 900.000, 80),
                    new ProductEntity("Procesador", "Rendimiento", 700.000, 45)).forEach(productRepository::save);
        }
    }

    public ProductEntity createProduct(String name, String category, double price, int stock) {
        validateProduct(name, price, stock);
        ProductEntity newProduct = new ProductEntity(name, category, price, stock);
        return productRepository.save(newProduct);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public Optional<ProductEntity> updateProduct(UUID id, String name, String category, double price, int stock) {
        validateProduct(name, price, stock);
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(name);
                    product.setCategory(category);
                    product.setPrice(price);
                    product.setStock(stock);
                    return productRepository.save(product);
                });
    }

    public boolean deleteProduct(UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateProduct(String name, double price, int stock) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }
}