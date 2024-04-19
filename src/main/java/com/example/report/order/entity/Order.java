package com.example.report.order.entity;

import com.example.report.order.common.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name="order_purchase")
public class Order {
    @Id
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "total_amount")
    private Long totalAmount;
    /**
     * Hibernate Mapping to shop tight coupled relation
//    @OneToMany
//    @JoinColumn(name = "order_line_item_id",referencedColumnName = "id")
//    private List<OrderLineItem> orderLineItems = new ArrayList<>();
     */
    @Enumerated
    private OrderStatus status;

}
