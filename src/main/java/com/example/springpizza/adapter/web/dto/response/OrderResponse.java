package com.example.springpizza.adapter.web.dto.response;

import java.util.List;

public record OrderResponse(Long id,
                            List<DishResponse> dishes,
                            Integer cutleryCount) {
}
