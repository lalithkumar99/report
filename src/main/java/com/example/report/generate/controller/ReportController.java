package com.example.report.generate.controller;

import com.example.report.customer.service.AddressService;
import com.example.report.customer.service.CustomerService;
import com.example.report.generate.service.ReportService;
import com.example.report.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final AddressService addressService;
    private final CustomerService customerService;
    private final PurchaseService purchaseService;
    private final ReportService reportService;

    @Autowired
    public ReportController(AddressService addressService, CustomerService customerService, PurchaseService purchaseService, ReportService reportService) {
        this.addressService = addressService;
        this.customerService = customerService;
        this.purchaseService = purchaseService;
        this.reportService = reportService;
    }

    @GetMapping(path = "state-wise-sale")
    public ResponseEntity<List<Object[]>> getSaleStateWise() {
        List<Object[]> saleReportStateWise = addressService.getSaleReportState();
        if (Objects.isNull(saleReportStateWise) || saleReportStateWise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(saleReportStateWise, HttpStatus.OK);
    }

    @GetMapping(path = "customer-order-count")
    public ResponseEntity<List<Object[]>> getCustomerBasedOnOrder() {
        List<Object[]> customerBasedOnOrder = customerService.getCustomerBasedOnOrder();
        if (Objects.isNull(customerBasedOnOrder)|| customerBasedOnOrder.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerBasedOnOrder, HttpStatus.OK);
    }
    @GetMapping(path = "profit-order")
    public ResponseEntity<List<Object[]>> getProfitBasedOnOrder() {
        List<Object[]> profit = purchaseService.getOrderProfit();
        if (Objects.isNull(profit) || profit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }

    @GetMapping(path = "customer-report")
    public ResponseEntity<List<Object[]>> getCustomerReport() {
        List<Object[]> customerReportWithMostOrder = reportService.generateCustomerReportWithMostOrder();
        if (Objects.isNull(customerReportWithMostOrder)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerReportWithMostOrder, HttpStatus.OK);
    }

    @GetMapping(path = "regional-sale-report")
    public ResponseEntity<List<Object[]>> getOrderReport() {
        List<Object[]> stateWiseRegionalReport = reportService.generateStateWiseSaleReport();
        if (Objects.isNull(stateWiseRegionalReport)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stateWiseRegionalReport, HttpStatus.OK);
    }

    @GetMapping(path = "profit-report")
    public ResponseEntity<List<Object[]>> getPurchaseReport() {
        List<Object[]> profitReport = reportService.generateProfitReport();
        if (Objects.isNull(profitReport)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profitReport, HttpStatus.OK);
    }
}
