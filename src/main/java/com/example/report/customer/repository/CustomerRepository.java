package com.example.report.customer.repository;

import com.example.report.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    @Query(value = "SELECT c.name, c.email, COUNT(*) AS order_count\n" +
            "    FROM customer.Customer c\n" +
            "    INNER JOIN orderdb.Order_purchase o ON c.id = o.customer_id\n" +
            "    GROUP BY c.id, c.name, c.email\n" +
            "    ORDER BY order_count DESC\n" +
            "    LIMIT 50;", nativeQuery = true)
    public List<Object[]> getCustomersWithMostOrder();


}
