package com.assignment.productapi.controller;

import com.assignment.productapi.dto.ProductDTO;
import com.assignment.productapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(service.getByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,
                                             @Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(service.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}