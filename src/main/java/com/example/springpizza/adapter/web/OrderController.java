package com.example.springpizza.adapter.web;

import com.example.springpizza.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/api/pizza/v1", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    OrderService orderService;

    @PostMapping("/order")
    public Long createOrder() {
        return orderService.createOrder();
    }
}
