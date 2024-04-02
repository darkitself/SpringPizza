package com.example.springpizza.config.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties("pizza.email")
public class EmailConfigProperties {

    List<String> recipients;
}
