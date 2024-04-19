package com.example.report.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer {
    @Id
    private Long id;
    private String name;
    private String email;
    /**
     * Hibernate mapping implementation for tightly coupled relation
//    @OneToOne
//    @JoinColumn(name="address_id", referencedColumnName = "id")
//    @OneToMany
//    @JoinColumn(name="customer_id", referencedColumnName = "id")
//    private Long orderId;
     */
    @Column(name = "address_id")
    private Long addressId;
}
