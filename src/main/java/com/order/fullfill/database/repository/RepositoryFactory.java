package com.order.fullfill.database.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RepositoryFactory {
    @Autowired
    private OrderRepositories orderRepositories;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WareHouseRepositories wareHouseRepositories;
}
