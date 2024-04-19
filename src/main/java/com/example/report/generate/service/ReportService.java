package com.example.report.generate.service;

import com.example.report.customer.service.AddressService;
import com.example.report.customer.service.CustomerService;
import com.example.report.purchase.service.PurchaseService;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ReportService {

    private final PurchaseService purchaseService;
    private final CustomerService customerService;
    private final AddressService addressService;

    @Autowired
    public ReportService(PurchaseService purchaseService, CustomerService customerService, AddressService addressService) {
        this.purchaseService = purchaseService;
        this.customerService = customerService;
        this.addressService = addressService;
    }

    public List<Object[]> generateCustomerReportWithMostOrder(){

        String csvFile = "C:/Users/Inno/Desktop/customer_report.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            List<Object[]> customerList = customerService.getCustomerBasedOnOrder();
            if(Objects.isNull(customerList) || customerList.isEmpty()){
                return null;
            }
            String[] header = {"Customer Name", "Customer Email", "No. Of Orders Made"};
            writer.writeNext(header);

            customerList.forEach(
                    record -> writer.writeNext(Arrays.stream(record)
                    .map(Object::toString)
                    .toArray(String[]::new)));

            System.out.println("CSV report generated successfully.");
            return customerList;
        } catch (Exception e) {
            System.err.println("Error generating CSV report: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> generateStateWiseSaleReport(){

        String csvFile = "C:/Users/Inno/Desktop/regional_sale_report.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            List<Object[]> addressServiceSaleReportStateList = addressService.getSaleReportState();
            if(Objects.isNull(addressServiceSaleReportStateList) || addressServiceSaleReportStateList.isEmpty()){
                return null;
            }
            String[] header = {"State Name", "Total Sales"};
            writer.writeNext(header);

            addressServiceSaleReportStateList.forEach(
                    record -> writer.writeNext(Arrays.stream(record)
                            .map(Object::toString)
                            .toArray(String[]::new)));

            System.out.println("CSV report generated successfully.");
            return addressServiceSaleReportStateList;
        } catch (Exception e) {
            System.err.println("Error generating CSV report: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> generateProfitReport(){

        String csvFile = "C:/Users/Inno/Desktop/profit_report.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            List<Object[]> saleTotalProfit = purchaseService.getOrderProfit();
            if(Objects.isNull(saleTotalProfit) || saleTotalProfit.isEmpty()){
                return null;
            }
            String[] header = {"Order Id", "Total Revenue","Total Cost","Total Profit"};
            writer.writeNext(header);

            saleTotalProfit.forEach(
                    record -> writer.writeNext(Arrays.stream(record)
                            .map(Object::toString)
                            .toArray(String[]::new)));

            System.out.println("CSV report generated successfully.");
            return saleTotalProfit;
        } catch (Exception e) {
            System.err.println("Error generating CSV report: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
