package com.example.springpizza.config;

import com.example.springpizza.service.common.Worker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class WorkerConfig {

    @Bean
    @Scope(value = "singleton")
    //Use this bean to show difference between singleton and prototype
    public Worker worker() {
        Worker worker = new Worker();
        worker.startWork();
        return worker;
    }
}
