package com.example.report.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="order_line_item")
public class OrderLineItem {
    @Id
    private Long id;
    @Column(name = "order_purchase_id")
    private Long orderPurchaseId;
    @Column(name = "product_id")
    private Long productId;
    private Long quantity;
    @Column(name = "unit_price")
    private Long unitPrice;

}
