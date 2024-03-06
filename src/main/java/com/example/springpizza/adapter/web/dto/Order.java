package com.example.springpizza.adapter.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Order(
        // Jackson in action
        @JsonProperty("id") Long orderId,
        String composite) {
}
