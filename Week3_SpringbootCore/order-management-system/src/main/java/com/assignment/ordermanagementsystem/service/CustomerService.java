package com.assignment.ordermanagementsystem.service;

import com.assignment.ordermanagementsystem.dto.CustomerDTO;
import com.assignment.ordermanagementsystem.entity.Customer;
import com.assignment.ordermanagementsystem.exception.ResourceNotFoundException;
import com.assignment.ordermanagementsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDTO dto) {
        if (customerRepository.findByEmail(dto.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public Page<Customer> getAllCustomers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return customerRepository.findAll(pageable);
    }

    public Customer updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = getCustomerById(id);
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.deleteById(id);
    }
}