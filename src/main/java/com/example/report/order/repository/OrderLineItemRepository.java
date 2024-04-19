package com.example.report.order.repository;

import com.example.report.order.entity.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem,Long>, JpaSpecificationExecutor<OrderLineItem> {
}
