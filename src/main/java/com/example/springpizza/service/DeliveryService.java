package com.example.springpizza.service;

import com.example.springpizza.service.common.Worker;
import com.example.springpizza.service.deliveryinfo.DeliveryInfoServiceApi;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
//Field injection used for this service
public class DeliveryService {

    @Autowired
    Worker worker;
    @Autowired
    DeliveryInfoServiceApi deliveryInfoServiceApi;

    public void deliverOrder(Long orderId) {
        worker.addJob(() -> {
            log.info("Start delivering order with id {}", orderId);
            try {
                var result = deliveryInfoServiceApi.getDeliveryInfo(orderId);
                log.info("Delivery info = {}", result);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            log.info("End delivering order with id {}", orderId);
        });
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Delivery service created");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Delivery service destroyed");
    }
}
