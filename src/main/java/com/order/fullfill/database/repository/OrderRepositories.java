package com.order.fullfill.database.repository;

import com.order.fullfill.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepositories extends JpaRepository<Order, Integer> {

    @Query(value = " select max(order_id) from orders ", nativeQuery = true)
    public Integer getMaxOrderId();
}
