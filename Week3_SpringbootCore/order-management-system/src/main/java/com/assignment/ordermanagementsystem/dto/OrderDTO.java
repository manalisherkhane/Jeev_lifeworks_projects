package com.assignment.ordermanagementsystem.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDTO {
    private Long customerId;

    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemDTO> items;
}
