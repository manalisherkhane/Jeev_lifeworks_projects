package com.assignment.logging.service;

import com.assignment.logging.dto.ProductRequest;
import com.assignment.logging.exception.BadRequestException;
import com.assignment.logging.exception.ResourceNotFoundException;
import com.assignment.logging.model.Product;
import com.assignment.logging.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        log.debug("Fetching all products from database");
        List<Product> products = productRepository.findAll();
        log.debug("Found {} products", products.size());
        return products;
    }

    public Product getProductById(Long id) {
        log.debug("Looking up product with id={}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(ProductRequest request) {
        if (request.getPrice() <= 0) {
            throw new BadRequestException("Price must be a positive value");
        }

        Product product = Product.builder()
                .name(request.getName())
                .category(request.getCategory())
                .price(request.getPrice())
                .stock(request.getStock() != null ? request.getStock() : 0)
                .build();

        Product saved = productRepository.save(product);
        log.info("Created new product: id={}, name={}", saved.getId(), saved.getName());
        return saved;
    }

    public Product updateProduct(Long id, ProductRequest request) {
        Product existing = getProductById(id);

        existing.setName(request.getName());
        existing.setCategory(request.getCategory());
        existing.setPrice(request.getPrice());
        if (request.getStock() != null) {
            existing.setStock(request.getStock());
        }

        Product updated = productRepository.save(existing);
        log.info("Updated product: id={}", updated.getId());
        return updated;
    }

    public void deleteProduct(Long id) {
        Product existing = getProductById(id);
        productRepository.delete(existing);
        log.info("Deleted product: id={}", id);
    }

    public List<Product> getByCategory(String category) {
        log.debug("Fetching products for category={}", category);
        return productRepository.findByCategory(category);
    }
}