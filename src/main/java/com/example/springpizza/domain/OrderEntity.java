package com.example.springpizza.domain;

import com.example.springpizza.adapter.web.dto.CreateOrderRequest;
import com.example.springpizza.domain.common.BaseDomainEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Table(name = "order")
@FieldDefaults(level = PRIVATE)
@FieldNameConstants
@NoArgsConstructor
public class OrderEntity extends BaseDomainEntity {

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDishRelation> dishes;

    Integer cutleryCount;

    public static OrderEntity createOrderFrom(List<DishEntity> dishes, CreateOrderRequest orderRequest) {
        OrderEntity order = new OrderEntity();
        order.dishes = dishes.stream()
                .map(d -> new OrderDishRelation(
                        order,
                        d,
                        orderRequest.dishesAndCount().get(d.getId())
                )).toList();
        order.cutleryCount = orderRequest.cutleryCount();
        return order;
    }
}
