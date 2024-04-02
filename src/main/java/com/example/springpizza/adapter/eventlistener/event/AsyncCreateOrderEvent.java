package com.example.springpizza.adapter.eventlistener.event;

import com.example.springpizza.domain.order.OrderEntity;
import com.example.springpizza.domain.user.UserEntity;

public record AsyncCreateOrderEvent(OrderEntity.OrderContext context, UserEntity userEntity) {
}
