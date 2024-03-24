package com.order.fullfill.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Table(name = "orders")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId", nullable = false)
    private Integer orderId;
    //
    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "customerId")
    private Integer customerId;

    @Column(name = "x_cord")
    private Double xCord;

    @Column(name = "y_cord")
    private Double yCord;

    @Column(name = "orderStatus")
    private String orderStatus;

    @Column(name = "totalAmount")
    private Double totalAmount;
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @Column(name = "warehouse_id")
    private Integer wareHouseId;

}
