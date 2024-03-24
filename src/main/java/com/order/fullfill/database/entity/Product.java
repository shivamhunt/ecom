package com.order.fullfill.database.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Table(name = "product")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;
}
