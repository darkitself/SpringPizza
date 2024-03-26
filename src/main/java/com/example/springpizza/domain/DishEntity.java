package com.example.springpizza.domain;

import com.example.springpizza.adapter.web.dto.request.CreateDishRequest;
import com.example.springpizza.domain.common.BaseDomainEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Table(name = "dish")
@FieldDefaults(level = PRIVATE)
@FieldNameConstants
@NoArgsConstructor
public class DishEntity extends BaseDomainEntity {

    @Type(JsonType.class)
    List<String> composition;

    BigDecimal price;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.REMOVE)
    List<OrderDishRelation> dishes;

    public static DishEntity createDishFrom(CreateDishRequest dishRequest) {
        DishEntity dish = new DishEntity();
        dish.composition = dishRequest.composition();
        dish.price = dishRequest.price();
        return dish;
    }
}
