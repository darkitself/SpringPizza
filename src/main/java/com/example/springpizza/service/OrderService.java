package com.example.springpizza.service;

import com.example.springpizza.service.common.Worker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Log4j2
//Setter injection used for this service
public class OrderService {

    Worker worker;

    public Long createOrder() {
        Long orderId = new Random().nextLong();
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

    @Autowired
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
