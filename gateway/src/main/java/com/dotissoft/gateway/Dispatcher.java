package com.dotissoft.gateway;

import java.time.Instant;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dispatcher {
    private final RabbitTemplate rabbitTemplate;
    
    public Dispatcher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void countFreeSpots(Instant from, Instant to) {
        rabbitTemplate.convertAndSend(
            "reservations-exchange", 
            "foo.bar",
            "hello"
        );
    }
}
