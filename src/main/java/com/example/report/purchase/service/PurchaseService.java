package com.example.report.purchase.service;

import com.example.report.purchase.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
    public List<Object[]> getOrderProfit(){
        return purchaseRepository.getOrderProfit();
    }
}
