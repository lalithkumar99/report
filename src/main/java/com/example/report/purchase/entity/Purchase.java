package com.example.report.purchase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
public class Purchase {
    @Id
    private Long id;
    @Column(name = "purchase_description")
    private String purchaseDescription;
    /**
    * Hibernate mapping to show tight coupled relation
//    @ManyToMany()
//    @JoinTable(
//            name = "purchase_products",
//            joinColumns = @JoinColumn(
//                    name = "purchase_id", referencedColumnName = "id"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "product_id", referencedColumnName = "id"
//            )
//    )
//    private Set<Product> products = new HashSet<>();
    */
    @Column(name = "total_price")
    private Long totalPrice;
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;
    private Long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id.equals(purchase.id) && purchaseDescription.equals(purchase.purchaseDescription) && quantity.equals(purchase.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDescription,quantity);
    }
}
