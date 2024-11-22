package com.spring.products.service;

import com.spring.products.entity.ProductEntity;
import com.spring.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductEntity testProduct;
    private final UUID testId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        testProduct = new ProductEntity("Test Product", "Test Category", 100.0, 10);
    }

    @Test
    void createProduct_Success() {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(testProduct);

        ProductEntity result = productService.createProduct(
            testProduct.getName(),
            testProduct.getCategory(),
            testProduct.getPrice(),
            testProduct.getStock()
        );

        assertNotNull(result);
        assertEquals(testProduct.getName(), result.getName());
        verify(productRepository).save(any(ProductEntity.class));
    }

    @Test
    void createProduct_InvalidPrice() {
        assertThrows(IllegalArgumentException.class, () ->
            productService.createProduct("Test", "Category", -1.0, 10)
        );
    }

    @Test
    void getAllProducts_Success() {
        List<ProductEntity> products = Arrays.asList(testProduct);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductEntity> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository).findAll();
    }

    @Test
    void getProductById_Success() {
        when(productRepository.findById(testId)).thenReturn(Optional.of(testProduct));

        Optional<ProductEntity> result = productService.getProductById(testId);

        assertTrue(result.isPresent());
        assertEquals(testProduct.getName(), result.get().getName());
        verify(productRepository).findById(testId);
    }

    @Test
    void updateProduct_Success() {
        when(productRepository.findById(testId)).thenReturn(Optional.of(testProduct));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(testProduct);

        Optional<ProductEntity> result = productService.updateProduct(
            testId,
            "Updated Name",
            "Updated Category",
            200.0,
            20
        );

        assertTrue(result.isPresent());
        assertEquals("Updated Name", result.get().getName());
        verify(productRepository).save(any(ProductEntity.class));
    }

    @Test
    void deleteProduct_Success() {
        when(productRepository.existsById(testId)).thenReturn(true);

        boolean result = productService.deleteProduct(testId);

        assertTrue(result);
        verify(productRepository).deleteById(testId);
    }
}