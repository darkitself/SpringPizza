package com.example.springpizza.domain;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class OrderEntity {

    Long id;
    List<String> composition;
    Integer cutleryCount;
}
