// RabbitMQReceiver.java
package com.sevyh.sevyhchatmediaservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receive(Object message) {
        // Process the received message
        // ...
    }
}
