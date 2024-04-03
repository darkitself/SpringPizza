package com.example.springpizza.domain.audit;

import com.example.springpizza.domain.common.BaseDomainEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Table(name = "audit")
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
public class AuditEntity extends BaseDomainEntity {

    String eventType;

    Long userId;

    @Enumerated(EnumType.STRING)
    AuditableResult status;

    String message;

    public AuditEntity(String eventType,
                       Long userId,
                       AuditableResult status,
                       String message) {
        this.eventType = eventType;
        this.status = status;
        this.userId = userId;
        this.message = message;
    }
}
