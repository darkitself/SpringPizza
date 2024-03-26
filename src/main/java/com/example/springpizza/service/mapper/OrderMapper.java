package com.example.springpizza.service.mapper;

import com.example.springpizza.adapter.web.dto.response.OrderResponse;
import com.example.springpizza.domain.OrderEntity;
import org.mapstruct.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper(componentModel = "spring",
        uses = {DishMapper.class})
public abstract class OrderMapper {

    @Transactional(propagation = Propagation.MANDATORY)
    public abstract OrderResponse entityToResponse(OrderEntity order);
}
