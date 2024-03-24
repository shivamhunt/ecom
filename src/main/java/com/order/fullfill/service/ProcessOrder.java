package com.order.fullfill.service;

import com.order.fullfill.database.entity.Order;
import com.order.fullfill.database.repository.OrderRepositories;
import com.order.fullfill.database.repository.RepositoryFactory;
import com.order.fullfill.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ProcessOrder implements IProcessOrder {
    @Autowired
    private OrderRepositories orderRepositories;
    @Autowired
    private RepositoryFactory repositoryFactory;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void processOrder(OrderDto req) {
        log.info("order placed ");
        Integer maxOrderId = null;
        try {
            maxOrderId = repositoryFactory.getOrderRepositories().getMaxOrderId();
        } catch (Exception e) {
            maxOrderId = 1;
        }

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setOrderId(maxOrderId + 1);
        order.setOrderStatus(Constant.PLACED);
        order.setXCord(req.getX());
        order.setYCord(req.getY());
        order.setCustomerId(req.getCustomerId());
        order.setTotalAmount(req.getTotalAmount());
        order.setProductId(req.getProductId());
        order.setOrderQuantity(req.getOrderQuantity());

        rabbitTemplate.convertAndSend(Constant.EXCHANGE, Constant.QUEUE, order);


    }


}
