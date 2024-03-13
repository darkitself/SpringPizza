package com.example.springpizza.adapter.web.dto;

import com.example.springpizza.adapter.web.validation.CutleryConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@CutleryConstraint
public record CompositionIn(@NotEmpty @Size(max = 2) List<String> composition,
                            @NotNull Boolean needCutlery,
                            @Positive Integer cutleryCount) {

    //variant with assert
//    @AssertTrue(message = "If you need cutlery, you should specify count")
//    public boolean isValidCutlery() {
//        return !needCutlery || cutleryCount != null;
//    }
}
