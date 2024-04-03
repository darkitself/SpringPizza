package com.example.springpizza.service.factory;

import com.example.springpizza.adapter.repository.DishRepository;
import com.example.springpizza.adapter.web.dto.request.CreateOrderRequest;
import com.example.springpizza.domain.order.OrderDishRelation;
import com.example.springpizza.domain.order.OrderEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderFactory {

    DishRepository dishRepository;

    public OrderEntity.OrderContext createContext(CreateOrderRequest orderRequest) {

        List<OrderDishRelation> dishes = dishRepository.findAllById(orderRequest.dishesAndCount().keySet())
                .stream()
                .map(d -> new OrderDishRelation(
                                null,
                                d,
                                orderRequest.dishesAndCount().get(d.getId())
                        )
                ).toList();

        return new OrderEntity.OrderContext(
                dishes,
                orderRequest.cutleryCount()
        );
    }
}
