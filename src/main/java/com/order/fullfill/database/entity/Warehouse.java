package com.order.fullfill.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "warehouse")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Warehouse {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "x_coordinate")
    private Double xCoordinate;
    @Column(name = "y_coordinate")
    private Double yCoordinate;
}
