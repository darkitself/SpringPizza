package com.example.springpizza.config;

import com.example.springpizza.service.deliveryinfo.DeliveryInfoServiceApi;
import com.example.springpizza.service.deliveryinfo.EmulatorDeliveryInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.example.springpizza.common.Profile.DEV;

@Profile(DEV)
//@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "DEV")
@Configuration
public class EmulatorDeliveryInfoOrderConfig {

    @Value("${pizza.order.delivery.range}")
    private Integer range;

    @Bean
    public DeliveryInfoServiceApi deliveryInfoServiceApi() {
        return new EmulatorDeliveryInfoService(range);
    }
}
