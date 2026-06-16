package com.assignment.ordermanagementsystem.repository;

import com.assignment.ordermanagementsystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}