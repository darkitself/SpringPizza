package com.example.springpizza.adapter.web;

import com.example.springpizza.adapter.web.dto.CompositionIn;
import com.example.springpizza.adapter.web.dto.Order;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/api/pizza/v1", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    OrderApi orderService;

    @PostMapping(value = "/order", consumes = APPLICATION_JSON_VALUE)
    public Long createOrder(@RequestBody CompositionIn composition) {
        return orderService.createOrder(composition);
    }

    @DeleteMapping("/order/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.removeOrder(orderId);
    }

    @GetMapping("/order/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/order")
    public Map<Long, String> getOrders(@Min(0) @RequestParam("size") Long ordersCount) {
        return orderService.getOrdersWithComposition(ordersCount);
    }

    // Example for personal exception handler
//    @ExceptionHandler
//    public ErrorResponse handleNotFound(NotFoundException ex) {
//        return new ErrorResponse("NOT_FOUND_ORDER", ex.getLocalizedMessage());
//    }
}
