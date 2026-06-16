package com.assignment.ordermanagementsystem.service;

import com.assignment.ordermanagementsystem.dto.OrderDTO;
import com.assignment.ordermanagementsystem.dto.OrderItemDTO;
import com.assignment.ordermanagementsystem.entity.Customer;
import com.assignment.ordermanagementsystem.entity.Order;
import com.assignment.ordermanagementsystem.entity.OrderItem;
import com.assignment.ordermanagementsystem.entity.Product;
import com.assignment.ordermanagementsystem.exception.ResourceNotFoundException;
import com.assignment.ordermanagementsystem.repository.CustomerRepository;
import com.assignment.ordermanagementsystem.repository.OrderRepository;
import com.assignment.ordermanagementsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public Order createOrder(OrderDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + dto.getCustomerId()));

        Order order = new Order();
        order.setCustomer(customer);

        for (OrderItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemDTO.getProductId()));

            if (product.getStock() < itemDTO.getQuantity())
                throw new IllegalArgumentException("Insufficient stock for: " + product.getName());

            product.setStock(product.getStock() - itemDTO.getQuantity());
            productRepository.save(product);

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(product.getPrice());
            order.getItems().add(item);
        }

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    public Page<Order> getAllOrders(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return orderRepository.findAll(pageable);
    }

    public Page<Order> getOrdersByCustomer(Long customerId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    public Order updateStatus(Long id, Order.OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepository.deleteById(id);
    }
}