package com.example.springpizza.domain.order.event;

import com.example.springpizza.common.domain.DomainEvent;
import com.example.springpizza.domain.order.OrderEntity;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends DomainEvent {

    OrderEntity orderEntity;
}
