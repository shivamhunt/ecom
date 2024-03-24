package com.order.fullfill.database.repository;

import com.order.fullfill.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {


}
