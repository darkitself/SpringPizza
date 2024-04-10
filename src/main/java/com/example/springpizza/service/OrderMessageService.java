package com.example.springpizza.service;

import com.example.springpizza.domain.order.OrderEntity;
import com.example.springpizza.service.messages.OrderCancelRequest;
import com.example.springpizza.service.messages.OrderCancelResponse;
import com.example.springpizza.service.messages.OrderCreateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderMessageService {

    RabbitTemplate rabbitTemplate;

    public void sendOrderCreate(OrderEntity orderEntity) {
        var request = new OrderCreateRequest(orderEntity.getId(), orderEntity.getCutleryCount());
        rabbitTemplate.convertAndSend("pizza.order", "order.create.rq", request);
    }

    public OrderCancelResponse sendOrderCancel(Long orderId) {
        var request = new OrderCancelRequest(orderId);
        return rabbitTemplate.convertSendAndReceiveAsType("pizza.order", "order.cancel.rq", request, new ParameterizedTypeReference<>() {});
    }
}
