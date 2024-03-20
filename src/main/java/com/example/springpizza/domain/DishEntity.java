package com.example.springpizza.domain;

import com.example.springpizza.domain.common.BaseDomainEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Entity;
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
}
