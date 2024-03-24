package com.order.fullfill.service;

import com.order.fullfill.database.entity.Order;
import com.order.fullfill.database.entity.Product;
import com.order.fullfill.database.entity.Warehouse;
import com.order.fullfill.database.repository.RepositoryFactory;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
@Transactional
public class HandleOrder {
    private static final int THREAD_POOL_SIZE = 10;
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    @Autowired
    private RepositoryFactory repositoryFactory;

    @RabbitListener(queues = Constant.QUEUE)
    public void orderListener(Order order) {

        order.setOrderStatus(Constant.PROCESS);
        log.info("changing order to process");
        repositoryFactory.getOrderRepositories().save(order);
        log.info(" order is set  to process");
        // finding the nearest warehouse


        executor.submit(() -> {
            findWarehouse(order);
            log.info(" modifying the product quantity");
            Product product = repositoryFactory.getProductRepository().getOne(order.getProductId());
            Integer quantity = product.getQuantity() - order.getOrderQuantity();
            product.setQuantity(quantity);
            repositoryFactory.getProductRepository().save(product);
            log.info(" order {} ", order);
            log.info(" modified the product quantity");
        });
    }

    public void findWarehouse(Order order) {
        log.info(" finding nearest warehouse");
        Integer nearestWarehouse = getNearestWarehouse(order.getXCord(), order.getYCord());
        order.setWareHouseId(nearestWarehouse);
        log.info(" updating status after finding nearest warehouse");
        order.setOrderStatus(Constant.PACKAGE);
        repositoryFactory.getOrderRepositories().save(order);
    }

    public Integer getNearestWarehouse(Double x, Double y) {
        double minDistance = Double.MAX_VALUE;
        int nearestWarehouseId = -1;
        List<Warehouse> allWareHouse = repositoryFactory.getWareHouseRepositories().getAllWareHouse();
        for (Warehouse w : allWareHouse) {
            Double xCoordinate = w.getXCoordinate();
            Double yCoordinate = w.getYCoordinate();
            double distance = Math.sqrt(Math.pow(x - xCoordinate, 2) + Math.pow(y - yCoordinate, 2));
            if (distance < minDistance) {
                minDistance = distance;
                nearestWarehouseId = w.getId();
            }
        }
        return nearestWarehouseId;

    }
}
