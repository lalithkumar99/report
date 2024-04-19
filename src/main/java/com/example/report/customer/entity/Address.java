package com.example.report.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
    @Id
    private Long id;
    /**
     * Hibernate implementation of mapping if it were tightly coupled
//    @OneToOne(mappedBy = "address")
//    private Long customerId;
     */
    private String country;
    private String state;
    private String city;
}
