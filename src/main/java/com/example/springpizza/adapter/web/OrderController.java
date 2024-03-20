package com.example.springpizza.adapter.web;

import com.example.springpizza.adapter.web.dto.CreateOrderRequest;
import com.example.springpizza.domain.OrderEntity;
import com.example.springpizza.service.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/api/pizza/v1", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    OrderService orderService;

    @PostMapping(value = "/order", consumes = APPLICATION_JSON_VALUE)
    public OrderEntity createOrder(@Valid @RequestBody CreateOrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @DeleteMapping("/order/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.removeOrder(orderId);
    }

    @GetMapping("/order/{orderId}")
    public OrderEntity getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    // Example for personal exception handler
//    @ExceptionHandler
//    public ErrorResponse handleNotFound(NotFoundException ex) {
//        return new ErrorResponse("NOT_FOUND_ORDER", ex.getLocalizedMessage());
//    }
}
