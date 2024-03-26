package com.example.springpizza.service.mapper;

import com.example.springpizza.adapter.web.dto.response.DishResponse;
import com.example.springpizza.domain.DishEntity;
import com.example.springpizza.domain.OrderDishRelation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper(componentModel = "spring")
public abstract class DishMapper {

    @Transactional(propagation = Propagation.MANDATORY)
    public abstract DishResponse entityToResponse(DishEntity dish);

    @Transactional(propagation = Propagation.MANDATORY)
    @Mapping(target = ".", source = "dish")
    @Mapping(target = "count", source = "count")
    public abstract DishResponse entityToResponse(OrderDishRelation dishRelation);
}
