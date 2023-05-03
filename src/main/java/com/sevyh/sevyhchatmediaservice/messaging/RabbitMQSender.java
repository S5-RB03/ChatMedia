package com.sevyh.sevyhchatmediaservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter messageConverter;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    public RabbitMQSender(RabbitTemplate rabbitTemplate, @Qualifier("rabbitMessageConverter") Jackson2JsonMessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    public void send(Object message) {
        org.springframework.amqp.core.Message convertedMessage = messageConverter.toMessage(message, null);
        rabbitTemplate.convertAndSend(exchange, routingKey, convertedMessage);
        System.out.println("Message sent to RabbitMQ: " + message.toString());
    }
    
}
