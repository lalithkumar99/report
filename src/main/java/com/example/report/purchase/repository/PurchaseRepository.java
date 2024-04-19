package com.example.report.purchase.repository;

import com.example.report.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long>, JpaSpecificationExecutor<Purchase> {
    @Query(value = "SELECT order_id,total_revenue,total_cost,total_revenue - total_cost AS total_profit\n" +
            "        FROM (\n" +
            "        SELECT SUM(o.total_amount) AS total_revenue,\n" +
            "        SUM(COALESCE(p.unit_price * oli.quantity, 0)) AS total_cost,\n" +
            "        o.id AS order_id\n" +
            "        FROM orderdb.Order_purchase o\n" +
            "        INNER JOIN orderdb.Order_Line_Item oli ON o.id = oli.order_purchase_id\n" +
            "        LEFT JOIN purchase.Product p ON oli.product_id = p.id\n" +
            "        GROUP BY o.id\n" +
            "        )AS total_profit;", nativeQuery = true)
    List<Object[]> getOrderProfit();
}



