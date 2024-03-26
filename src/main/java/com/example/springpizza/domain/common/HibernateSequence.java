package com.example.springpizza.domain.common;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.NONE;

@NoArgsConstructor(access = NONE)
public final class HibernateSequence {

    public static final String NAME = "hibernate_sequence";
    public static final int ALLOCATION_SIZE = 30;
}

