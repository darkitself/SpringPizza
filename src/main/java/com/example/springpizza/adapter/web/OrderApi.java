package com.example.springpizza.adapter.web;

import com.example.springpizza.adapter.web.dto.CompositionIn;
import com.example.springpizza.adapter.web.dto.Order;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Validated
public interface OrderApi {

    Long createOrder(@Valid CompositionIn composition);

    Map<Long, String> getOrdersWithComposition(Long ordersCount);

    Order getOrder(Long orderId);

    void removeOrder(Long orderId);
}
