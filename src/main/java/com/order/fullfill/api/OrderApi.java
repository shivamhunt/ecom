package com.order.fullfill.api;


import com.order.fullfill.dto.OrderDto;
import com.order.fullfill.service.IProcessOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OrderApi {
    @Autowired
    private IProcessOrder processOrder;

    @PostMapping(value = "/saveOrder")
    public OrderDto sendOrder(@RequestBody OrderDto order) {
        log.info(" order {} ", order);
        processOrder.processOrder(order);
        return order;
    }

    @PutMapping(value = "/saveOrder1")
    public OrderDto updateOrder(@RequestBody OrderDto order) {
        log.info(" order {} ", order);
//        processOrder.processOrder(order);
        return order;
    }

    @GetMapping(value = "/myapp")
    public String defaultScreen(@RequestParam(defaultValue = "hello") String name) {
        return name + " app started";
    }

}
