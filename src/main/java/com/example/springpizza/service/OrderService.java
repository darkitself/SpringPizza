package com.example.springpizza.service;

import com.example.springpizza.adapter.web.dto.CompositionIn;
import com.example.springpizza.adapter.web.dto.Order;
import com.example.springpizza.adapter.web.errors.NotFoundException;
import com.example.springpizza.service.common.Worker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Log4j2
//Setter injection used for this service
public class OrderService {

    Worker worker;
    Map<Long, String> orders = new ConcurrentHashMap<>();

    @Autowired
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Long createOrder(CompositionIn composition) {
        Long orderId = new Random().nextLong();
        orders.put(orderId, composition.composition());
        worker.addJob(() -> {
            log.info("Start creating order with id {}", orderId);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("End creating order with id {}", orderId);
        });
        return orderId;
    }

    public Map<Long, String> getOrders() {
        return orders;
    }

    public Order getOrder(Long orderId) {
        if (!orders.containsKey(orderId)) {
            throw new NotFoundException(orderId);
        }
        return new Order(orderId, orders.get(orderId));
    }


    public void removeOrder(Long orderId) {
        orders.remove(orderId);
    }
}
