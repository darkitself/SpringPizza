package com.example.springpizza.config;

import com.example.springpizza.config.properties.EmailConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(EmailConfigProperties.class)
public class EmailConfig {
}
