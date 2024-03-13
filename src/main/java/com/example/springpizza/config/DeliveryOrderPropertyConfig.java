package com.example.springpizza.config;

import com.example.springpizza.config.property.DeliveryOrderProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DeliveryOrderProperties.class)
public class DeliveryOrderPropertyConfig {


}
