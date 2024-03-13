package com.example.springpizza.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("pizza.order.delivery")
public class DeliveryOrderProperties {

    boolean enable;
}
