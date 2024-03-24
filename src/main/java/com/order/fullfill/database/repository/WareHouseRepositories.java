package com.order.fullfill.database.repository;

import com.order.fullfill.database.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface WareHouseRepositories extends JpaRepository<Warehouse, Integer> {
    @Query(value = " select * from warehouse where id is not null\n ", nativeQuery = true)
    public List<Warehouse> getAllWareHouse();


}
