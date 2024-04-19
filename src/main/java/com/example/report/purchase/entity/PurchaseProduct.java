package com.example.report.purchase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "purchase_product")
public class PurchaseProduct {
    @Id
    private Long id;
    @Column(name = "purchase_id")
    private Long purchaseId;
    @Column(name = "product_id")
    private Long productId;
}
