package com.example.springpizza.service.message;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DeliveryResponse(
        Long orderId,
        LocalDateTime startDelivery,
        LocalDateTime endDelivery
) {

}
