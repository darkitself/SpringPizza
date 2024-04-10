package com.example.springpizza.adapter.web.errors;

import lombok.Getter;

@Getter
public class MessageException extends RuntimeException {

    private final String code = "EXCHANGE_ERROR";

    public MessageException(String message) {
        super(message);
    }
}
