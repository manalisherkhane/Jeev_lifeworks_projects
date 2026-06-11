package com.assignment.productapi.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;

    @Min(0)
    private Integer stock;
}