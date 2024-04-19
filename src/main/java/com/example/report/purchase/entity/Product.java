package com.example.report.purchase.entity;

import com.example.report.order.common.StockStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private String category;
    @Enumerated
    private StockStatus status;
    @Column(name="unit_price")
    private Long unitPrice;
    /**
     * Hibernate mapping to show tight coupled relation
//    @OneToMany()
//    @JsonIgnore
//    @JoinColumn(name = "order_line_item_id", referencedColumnName = "id")
//    private List<OrderLineItem> orderLineItems = new ArrayList<>();
//    @ManyToMany(mappedBy = "products")
//    @JsonIgnore
//    private Set<Purchase> purchases = new HashSet<>();
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
