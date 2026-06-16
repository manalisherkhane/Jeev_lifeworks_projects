package com.assignment.ordermanagementsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDTO {
    @NotNull private Long productId;
    @Min(1) private Integer quantity;
}