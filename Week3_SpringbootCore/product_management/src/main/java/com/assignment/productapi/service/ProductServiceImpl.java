package com.assignment.productapi.service;

import com.assignment.productapi.dto.ProductDTO;
import com.assignment.productapi.exception.ProductNotFoundException;
import com.assignment.productapi.model.Product;
import com.assignment.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    private final Function<Product, ProductDTO> toDTO = p ->
        ProductDTO.builder()
            .id(p.getId()).name(p.getName())
            .category(p.getCategory())
            .price(p.getPrice()).stock(p.getStock())
            .build();

    private final Function<ProductDTO, Product> toEntity = d ->
        Product.builder()
            .name(d.getName()).category(d.getCategory())
            .price(d.getPrice()).stock(d.getStock())
            .build();

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        return toDTO.apply(repo.save(toEntity.apply(dto)));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return repo.findById(id).map(toDTO)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return repo.findAll().stream().map(toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getByCategory(String category) {
        return repo.findByCategory(category).stream().map(toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product existing = repo.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        existing.setName(dto.getName());
        existing.setCategory(dto.getCategory());
        existing.setPrice(dto.getPrice());
        existing.setStock(dto.getStock());
        return toDTO.apply(repo.save(existing));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!repo.existsById(id))
            throw new ProductNotFoundException("Product not found with id: " + id);
        repo.deleteById(id);
    }
}