package com.example.springpizza.service;

import com.example.springpizza.service.messages.OrderCreateResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderHandlerService {

    OrderService orderService;

    @RabbitListener(queues = "pizza.order.create.rs")
    public void orderStatesChangeHandler(OrderCreateResponse orderCreateResponse) {
        log.info("Receive: {}", orderCreateResponse);
        orderService.updateOrderStatus(orderCreateResponse.orderId(), orderCreateResponse.status());
    }
}
