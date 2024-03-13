package com.example.springpizza.adapter.web.validation;

import com.example.springpizza.adapter.web.dto.CompositionIn;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CutleryValidator implements ConstraintValidator<CutleryConstraint, CompositionIn> {

    @Override
    public boolean isValid(CompositionIn value, ConstraintValidatorContext context) {
        if (value.needCutlery()) {
            return value.cutleryCount() != null && value.cutleryCount() > 0 && value.cutleryCount() < 10;
        }

        return true;
    }
}
