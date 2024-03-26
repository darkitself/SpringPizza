package com.example.springpizza.adapter.web.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record DishResponse(Long id,
                           List<String> composition,
                           BigDecimal price,
                           Integer count) {

}