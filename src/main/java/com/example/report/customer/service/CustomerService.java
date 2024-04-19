package com.example.report.customer.service;

import com.example.report.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Object[]> getCustomerBasedOnOrder(){
        return customerRepository.getCustomersWithMostOrder();
    }
}
