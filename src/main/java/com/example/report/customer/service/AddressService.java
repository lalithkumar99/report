package com.example.report.customer.service;

import com.example.report.customer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public List<Object[]> getSaleReportState(){
        return  addressRepository.getSaleReportState();
    }
}
