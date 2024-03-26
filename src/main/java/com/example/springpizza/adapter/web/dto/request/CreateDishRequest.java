package com.example.springpizza.adapter.web.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record CreateDishRequest(List<String> composition,
                                BigDecimal price) {
}
