package com.example.springpizza.service.messages;

import com.example.springpizza.domain.order.OrderStatus;

public record OrderCreateResponse(Long orderId, OrderStatus status) {
}
