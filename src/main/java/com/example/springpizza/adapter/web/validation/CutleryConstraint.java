package com.example.springpizza.adapter.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CutleryValidator.class)
@Target({ElementType.PARAMETER, ElementType.TYPE})
public @interface CutleryConstraint {

    String message() default "{pizza.order.cutlery.not_valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
