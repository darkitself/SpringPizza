package com.example.springpizza.domain.user;

import com.example.springpizza.domain.common.BaseDomainEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Table(name = "user_info")
@FieldDefaults(level = PRIVATE)
@FieldNameConstants
@NoArgsConstructor
public class UserEntity extends BaseDomainEntity implements UserDetails {
    String username;
    String password;
    @Type(JsonType.class)
    List<UserRole> authorities;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;

    public static UserEntity from(Context context) {
        UserEntity user = new UserEntity();
        user.username = context.username;
        user.password = context.password;
        user.authorities = context.authorities;
        user.accountNonExpired = true;
        user.accountNonLocked = true;
        user.credentialsNonExpired = true;
        user.enabled = true;
        return user;
    }

    public record Context(String username,
                          String password,
                          List<UserRole> authorities) {

    }
}
