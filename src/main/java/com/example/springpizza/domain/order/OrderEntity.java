package com.example.springpizza.domain.order;

import com.example.springpizza.domain.common.BaseDomainEntity;
import com.example.springpizza.domain.user.UserEntity;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    public OrderEntity(OrderContext cntx, UserEntity user) {
        dishes = cntx.dishes();
        dishes.forEach(d -> d.setOrder(this));
        cutleryCount = cntx.cutleryCount();
        this.user = user;
    }

    public record OrderContext(List<OrderDishRelation> dishes,
                               Integer cutleryCount) {

    }
}
