package com.example.springpizza.adapter.web.errors;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private String code = "NOT_FOUND";

    public NotFoundException(Long entityId) {
        super("Not found №" + entityId);
    }
}
