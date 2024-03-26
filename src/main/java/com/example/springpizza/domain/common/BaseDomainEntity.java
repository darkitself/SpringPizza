package com.example.springpizza.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@MappedSuperclass
@FieldNameConstants(innerTypeName = "BaseFields")
@FieldDefaults(level = PROTECTED)
public abstract class BaseDomainEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = HibernateSequence.NAME)
    @SequenceGenerator(name = HibernateSequence.NAME, allocationSize = HibernateSequence.ALLOCATION_SIZE)
    Long id;

    @JsonIgnore
    @Version
    Integer version;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BaseDomainEntity)) {
            return false;
        }

        var other = (BaseDomainEntity) o;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
