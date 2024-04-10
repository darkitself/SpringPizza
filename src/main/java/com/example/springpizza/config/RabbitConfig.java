package com.example.springpizza.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // На случай, если хочется побаловаться с rabbitTemplate
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
//        var rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter);
//        return rabbitTemplate;
//    }

    @Bean
    public Declarables pizzaOrderDeclarables() {

        var pizzaOrderRq = new Queue("pizza.order.create.rq", false);
        var pizzaOrdersRs = new Queue("pizza.order.create.rs", false);
        var pizzaOrderCancel = new Queue("pizza.order.cancel.rq", false);
        var pizzaOrderExchange = new DirectExchange("pizza.order");
        return new Declarables(
                pizzaOrderCancel,
                pizzaOrderRq,
                pizzaOrdersRs,
                pizzaOrderExchange,
                BindingBuilder.bind(pizzaOrderRq).to(pizzaOrderExchange).with("order.create.rq"),
                BindingBuilder.bind(pizzaOrdersRs).to(pizzaOrderExchange).with("order.create.rs"),
                BindingBuilder.bind(pizzaOrderCancel).to(pizzaOrderExchange).with("order.cancel.rq")
        );
    }

}
