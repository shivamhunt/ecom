package com.order.fullfill.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderDto {
    private Integer customerId;
    private Double x;
    private Double y;
    private Double totalAmount;
    private Integer productId;
    private Integer orderQuantity;


}
