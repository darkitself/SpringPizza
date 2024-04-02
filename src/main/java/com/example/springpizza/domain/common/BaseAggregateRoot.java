package com.example.springpizza.domain.common;

import com.example.springpizza.common.domain.DomainEvent;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@MappedSuperclass
@FieldDefaults(level = PRIVATE, makeFinal = true)
public abstract class BaseAggregateRoot {

    @Transient
    transient List<DomainEvent> events = new ArrayList<>();

    protected void registerEvents(DomainEvent event) {
        events.add(event);
    }

    @DomainEvents
    public Collection<DomainEvent> domainEvents() {
        return Collections.unmodifiableList(events);
    }

    @AfterDomainEventPublication
    public void clearDomainEvents() {
        events.clear();
    }
}
