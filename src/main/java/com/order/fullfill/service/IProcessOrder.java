package com.order.fullfill.service;

import com.order.fullfill.dto.OrderDto;

public interface IProcessOrder {

    public void processOrder(OrderDto order);

}
