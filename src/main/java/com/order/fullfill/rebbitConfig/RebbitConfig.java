package com.order.fullfill.rebbitConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RebbitConfig {
    @Bean
    public Queue fulfillmentQueue() {
        return new Queue("fulfillment-queue");
    }

    @Bean
    public DirectExchange warehouseExchange() {
        return new DirectExchange("warehouse-exchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(fulfillmentQueue()).to(warehouseExchange()).with("fulfillment-queue");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("com.order.fullfill.database.entity");
        converter.setClassMapper(classMapper);
        return converter;
    }
}
