package com.example.report.customer.repository;

import com.example.report.customer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>, JpaSpecificationExecutor<Address> {

    @Query(value = "SELECT r.state AS region_name, SUM(o.total_amount) AS total_sales\n" +
        "    FROM orderdb.Order_purchase o\n" +
        "    INNER JOIN customer.Customer c ON o.customer_id = c.id\n" +
        "    INNER JOIN customer.Address r ON c.address_id = r.id\n" +
        "    GROUP BY r.country, r.state\n" +
        "    ORDER BY total_sales DESC;", nativeQuery = true)
    public List<Object[]> getSaleReportState();

}
