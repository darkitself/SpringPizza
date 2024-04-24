package com.example.springpizza.config.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties("pizza.order")
public class OrderProperties {

    Duration ttl;
}
