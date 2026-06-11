package com.assignment.productapi.service;

import com.assignment.productapi.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO dto);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getByCategory(String category);
    ProductDTO updateProduct(Long id, ProductDTO dto);
    void deleteProduct(Long id);
}