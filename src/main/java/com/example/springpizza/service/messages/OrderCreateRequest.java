package com.example.springpizza.service.messages;

public record OrderCreateRequest(Long orderId, Integer cutleryCount) {
}
