package com.example.springpizza.adapter.web;

import com.example.springpizza.adapter.web.dto.request.CreateOrderRequest;
import com.example.springpizza.adapter.web.dto.response.OrderResponse;
import com.example.springpizza.common.metrics.counter.CounterMetric;
import com.example.springpizza.common.metrics.timer.TimerMetric;
import com.example.springpizza.domain.user.UserEntity;
import com.example.springpizza.service.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/api/pizza/v1", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    OrderService orderService;

    @PostMapping(value = "/order", consumes = APPLICATION_JSON_VALUE)
    @CounterMetric(name = "client_order_create_counter")
    @TimerMetric(name = "client_order_create_timer")
    public OrderResponse createOrder(@AuthenticationPrincipal UserEntity user,
                                     @Valid @RequestBody CreateOrderRequest orderRequest) {
        return orderService.createOrder(user, orderRequest);
    }

    @DeleteMapping("/order/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.removeOrder(orderId);
    }

    @GetMapping("/order/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }
}
