package com.example.springpizza.adapter.web.dto;

import java.util.Map;

public record CreateOrderRequest(Map<Long, Integer> dishesAndCount,
                                 Integer cutleryCount) {
    public CreateOrderRequest {
        if (cutleryCount == null) {
            cutleryCount = 0;
        }
    }
}
