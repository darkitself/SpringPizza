package com.example.springpizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Table(name = "order_dish")
@FieldDefaults(level = PRIVATE)
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderDishRelation.OrderDishRelationId.class)
public class OrderDishRelation {

    @Id
    @ManyToOne
    @Setter
    @JoinColumn(name = "order_id")
    @JsonIgnore
    OrderEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "dish_id")
    DishEntity dish;

    Integer count;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderDishRelationId implements Serializable {
        Long order;
        Long dish;
    }
}
